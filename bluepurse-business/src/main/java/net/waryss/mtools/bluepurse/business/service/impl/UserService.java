package net.waryss.mtools.bluepurse.business.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.waryss.mtools.bluepurse.business.service.IPurseService;
import net.waryss.mtools.bluepurse.business.service.IResourceService;
import net.waryss.mtools.bluepurse.business.service.ISpendingService;
import net.waryss.mtools.bluepurse.business.service.IUserService;
import net.waryss.mtools.bluepurse.business.utils.BluePurseBeanUtil;
import net.waryss.mtools.bluepurse.business.utils.BluePurseDateUtil;
import net.waryss.mtools.bluepurse.business.utils.ModelObjectMapper;
import net.waryss.mtools.bluepurse.dao.IParamDao;
import net.waryss.mtools.bluepurse.dao.IUserDao;
import net.waryss.mtools.bluepurse.dto.PurseDto;
import net.waryss.mtools.bluepurse.dto.ResourceDto;
import net.waryss.mtools.bluepurse.dto.SpendingDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.entity.UserEntity;
import net.waryss.mtools.bluepurse.exception.BluePurseErrorCode;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

@Service
public class UserService implements IUserService {

	private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private IUserDao userDao;
	@Autowired
	private ISpendingService chargeService;
	@Autowired
	private IPurseService plannerService;
	@Autowired
	private IResourceService provisionService;
	@Autowired
	private IParamDao configDao;

	@Override
	public UserDto createUser(UserDto user) throws BluePurseException {
		user.setSubscriptionDate(BluePurseDateUtil.now());
		UserEntity userEntity = ModelObjectMapper.userBean2Persistent(user);
		if (Objects.isNull(userEntity.getUserGroup())) {
			userEntity.setUserGroup(configDao.findDefaultUserGroup());
		}
		if (Objects.isNull(userEntity.getStatus())) {
			userEntity.setStatus(configDao.findDefaultStatus());
		}
		userDao.persist(userEntity);
		LOGGER.info("return created user #" + user.getPurser());
		return ModelObjectMapper.persistentUser2Bean(userEntity);
	}

	@Override
	public PurseDto createUserPurse(Long userId, PurseDto planner) throws BluePurseException {
		planner.setCreator(findUser(userId));
		return plannerService.createPurse(planner);
	}

	@Override
	public ResourceDto createUserResource(Long userId, ResourceDto provision) throws BluePurseException {
		provision.setOwner(findUser(userId));
		return provisionService.createResource(provision);
	}

	@Override
	public SpendingDto createUserSpending(Long userId, SpendingDto charge) throws BluePurseException {
		charge.setOwner(findUser(userId));
		return chargeService.createSpending(charge);
	}

	/**
	 * @param userId
	 * @return
	 */
	@Override
	public boolean existsWithId(Long userId) {
		return Objects.isNull(userDao.find(userId));
	}

	@Override
	public UserDto findUser(Long userId) throws BluePurseException {
		UserEntity userPersistent = userDao.find(userId);
		if (Objects.isNull(userPersistent)) {
			String detail = "Can't find user with id #" + userId;
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		LOGGER.info("get user by id #" + userId);
		return ModelObjectMapper.persistentUser2Bean(userPersistent);
	}

	@Override
	public PurseDto findUserPurse(Long userId, Long plannerId) throws BluePurseException {
		return plannerService.findByIdAndCreator(plannerId, findUser(userId));
	}

	@Override
	public List<PurseDto> findUserPurses(Long userId) throws BluePurseException {
		List<PurseDto> plannerList = plannerService.findByCreator(findUser(userId));
		return plannerList;
	}

	@Override
	public ResourceDto findUserResource(Long userId, Long provisionId) throws BluePurseException {
		return provisionService.findByIdAndUser(provisionId, findUser(userId));
	}

	@Override
	public List<ResourceDto> findUserResources(Long userId) throws BluePurseException {
		List<ResourceDto> provisionList = provisionService.findByUser(findUser(userId));
		return provisionList;
	}

	@Override
	public SpendingDto findUserSpending(Long userId, Long spendingId) throws BluePurseException {
		return chargeService.findByIdAndUser(spendingId, findUser(userId));
	}

	@Override
	public List<SpendingDto> findUserSpendings(Long userId) throws BluePurseException {
		List<SpendingDto> chargeList = chargeService.findByUser(findUser(userId));
		return chargeList;
	}

	@Override
	public List<UserDto> getAllUser() throws BluePurseException {
		return ModelObjectMapper.persistentUser2BeanList(userDao.findAll());
	}

	@Override
	public UserDto partialUpdateUser(Long userId, UserDto user) throws BluePurseException {
		UserDto oldUser = findUser(userId);
		BluePurseBeanUtil.partialCopyBean(oldUser, user);
		return updateUser(userId, oldUser);
	}

	@Override
	public PurseDto partialUpdateUserPurse(Long userId, Long plannerId, PurseDto planner) throws BluePurseException {
		PurseDto plannerToUpdate = plannerService.findByIdAndCreator(plannerId, findUser(userId));
		return plannerService.partialUpdatePurse(plannerId, plannerToUpdate);
	}

	@Override
	public ResourceDto partialUpdateUserResource(Long userId, Long provisionId, ResourceDto provision) throws BluePurseException {
		ResourceDto provisionToUpdate = provisionService.findByIdAndUser(provisionId, findUser(userId));
		return provisionService.partialUpdateResource(provisionId, provisionToUpdate);
	}

	@Override
	public SpendingDto partialUpdateUserSpending(Long userId, Long spendingId, SpendingDto spendingBean)
			throws BluePurseException {
		SpendingDto chargeToUpdate = chargeService.findByIdAndUser(spendingId, findUser(userId));
		return chargeService.partialUpdateSpending(spendingId, chargeToUpdate);
	}

	@Override
	public void removeUser(Long userId) throws BluePurseException {
		LOGGER.info("remove user #" + userId);
		if(!existsWithId(userId)){
			String detail = "Can't find user with id #" + userId;
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		UserEntity userEntity = userDao.find(userId);
		// TODO historiser avant de supprimer
		userDao.remove(userEntity);
	}

	@Override
	public void removeUserPurse(Long userId, Long plannerId) throws BluePurseException {
		if(plannerService.existsWithIdAndCreator(plannerId, findUser(userId))){
			plannerService.removePurse(plannerId);
		}
	}

	@Override
	public void removeUserResource(Long userId, Long provisionId) throws BluePurseException {
		if(provisionService.existsResourceWithIdAndUser(provisionId, findUser(userId))){
			provisionService.removeResource(provisionId);
		}
	}

	@Override
	public void removeUserSpending(Long userId, Long chargeId) throws BluePurseException {
		if(chargeService.existsWithIdAndUser(chargeId, findUser(userId))){
			chargeService.removeSpending(chargeId);
		}
	}

	public void setChargeService(ISpendingService chargeService) {
		this.chargeService = chargeService;
	}

	public void setPlannerService(IPurseService plannerService) {
		this.plannerService = plannerService;
	}

	public void setProvisionService(IResourceService provisionService) {
		this.provisionService = provisionService;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDto updateUser(Long userId, UserDto user) throws BluePurseException {
		if (Objects.isNull(user)) {
			String detail = "Can't update entity with null object";
			throw new BluePurseException(BluePurseErrorCode.BP_101, detail);
		}
		if (user.getPurser() != null && !userId.equals(user.getPurser())) {
			String detail = "Payload userId and url path paylod are differents ";
			throw new BluePurseException(BluePurseErrorCode.BP_103, detail);
		}
		UserEntity persistentUser = userDao.merge(ModelObjectMapper.userBean2Persistent(user));
		return ModelObjectMapper.persistentUser2Bean(persistentUser);
	}

	@Override
	public PurseDto updateUserPurse(Long userId, Long plannerId, PurseDto planner) throws BluePurseException {
		PurseDto plannerToUpdate = plannerService.findByIdAndCreator(plannerId, findUser(userId));
		return plannerService.updatePurse(plannerId, plannerToUpdate);
	}

	@Override
	public ResourceDto updateUserResource(Long userId, Long provisionId, ResourceDto provision) throws BluePurseException {
		ResourceDto provisionToUpdate = provisionService.findByIdAndUser(provisionId, findUser(userId));
		return provisionService.updateResource(provisionId, provisionToUpdate);
	}

	@Override
	public SpendingDto updateUserSpending(Long userId, Long chargeId, SpendingDto charge) throws BluePurseException {
		SpendingDto chargeToUpdate = chargeService.findByIdAndUser(chargeId, findUser(userId));
		return chargeService.updateSpending(chargeId, chargeToUpdate);
	}
}
