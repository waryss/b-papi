package net.waryss.mtools.bluepurse.business.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.waryss.mtools.bluepurse.business.service.ISpendingService;
import net.waryss.mtools.bluepurse.business.utils.BluePurseBeanUtil;
import net.waryss.mtools.bluepurse.business.utils.ModelObjectMapper;
import net.waryss.mtools.bluepurse.dao.IOutDao;
import net.waryss.mtools.bluepurse.dto.SpendingDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.entity.OutEntity;
import net.waryss.mtools.bluepurse.exception.BluePurseErrorCode;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

@Service
public class SpendingService implements ISpendingService {

	private static Logger LOGGER = LoggerFactory.getLogger(SpendingService.class);
	@Autowired
	private IOutDao chargeDao;

	@Override
	public SpendingDto findSpending(Long chargeId) throws BluePurseException {
		OutEntity chargePersistent = chargeDao.find(chargeId);
		if (Objects.isNull(chargePersistent)) {
			String detail = "Can't find charge with id #" + chargeId;
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		LOGGER.info("get charge by id #" + chargeId);
		return ModelObjectMapper.persistentOut2Bean(chargePersistent);
	}

	@Override
	public SpendingDto createSpending(SpendingDto chargeBean) throws BluePurseException {
		OutEntity chargePersistent = ModelObjectMapper.spending2Persistent(chargeBean);
		chargeDao.persist(chargePersistent);
		LOGGER.info("return created charge #" + chargePersistent.getId());
		return ModelObjectMapper.persistentOut2Bean(chargePersistent);
	}

	@Override
	public void removeSpending(Long chargeId) throws BluePurseException {
		LOGGER.info("remove charge #" + chargeId);
		OutEntity chargeEntity = chargeDao.find(chargeId);
		// TODO historiser avant de supprimer
		chargeDao.remove(chargeEntity);

	}

	@Override
	public List<SpendingDto> getAllSpending() throws BluePurseException {
		return ModelObjectMapper.persistentOut2BeanList(chargeDao.findAll());
	}

	@Override
	public SpendingDto updateSpending(Long chargeId, SpendingDto chargeToUpdate) throws BluePurseException {
		if (Objects.isNull(chargeToUpdate)) {
			String detail = "Can't update entity with null object";
			throw new BluePurseException(BluePurseErrorCode.BP_101, detail);
		}
		OutEntity persistentCharge = chargeDao.merge(ModelObjectMapper.spending2Persistent(chargeToUpdate));
		return ModelObjectMapper.persistentOut2Bean(persistentCharge);
	}

	@Override
	public SpendingDto partialUpdateSpending(Long chargeId, SpendingDto chargeToUpdate) throws BluePurseException {
		SpendingDto oldCharge = findSpending(chargeId);
		BluePurseBeanUtil.partialCopyBean(oldCharge, chargeToUpdate);
		return updateSpending(chargeId, oldCharge);
	}

	public void setChargeDao(IOutDao chargeDao) {
		this.chargeDao = chargeDao;
	}

	@Override
	public List<SpendingDto> findByUser(UserDto user) throws BluePurseException {
		List<OutEntity> chargePersistList = chargeDao.findByUser(ModelObjectMapper.userBean2Persistent(user));
		if (Objects.isNull(chargePersistList) || chargePersistList.isEmpty()) {
			String detail = "Can't find charges for user #" + user.getPurser();
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		return ModelObjectMapper.persistentOut2BeanList(chargePersistList);
	}

	@Override
	public SpendingDto findByIdAndUser(Long chargeId, UserDto user) throws BluePurseException {
		OutEntity chargePersist = chargeDao.findByIdAndUser(chargeId, ModelObjectMapper.userBean2Persistent(user));
		if (Objects.isNull(chargePersist)) {
			String detail = "Can't find charges for user #" + user.getPurser();
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		return ModelObjectMapper.persistentOut2Bean(chargePersist);
	}

	@Override
	public boolean existsWithIdAndUser(Long chargeId, UserDto user) throws BluePurseException {
		return ! Objects.isNull(findByIdAndUser(chargeId, user));
	}

}
