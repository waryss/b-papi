package net.waryss.mtools.bluepurse.business.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.waryss.mtools.bluepurse.business.service.IPurseService;
import net.waryss.mtools.bluepurse.business.utils.BluePurseBeanUtil;
import net.waryss.mtools.bluepurse.business.utils.ModelObjectMapper;
import net.waryss.mtools.bluepurse.dao.IPurseDao;
import net.waryss.mtools.bluepurse.dto.PurseDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.entity.PurseEntity;
import net.waryss.mtools.bluepurse.exception.BluePurseErrorCode;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

@Service
public class PurseService implements IPurseService {

	private static Logger LOGGER = LoggerFactory.getLogger(PurseService.class);
	@Autowired
	private IPurseDao plannerDao;

	@Override
	public PurseDto createPurse(PurseDto plannerBean) throws BluePurseException {
		PurseEntity plannerPersistent = ModelObjectMapper.purseBean2Persistent(plannerBean);
		plannerDao.persist(plannerPersistent);
		LOGGER.info("return created planner #" + plannerBean.getPurseId());
		return ModelObjectMapper.persistentPurse2Bean(plannerPersistent);
	}

	@Override
	public boolean existsWithIdAndCreator(Long plannerId, UserDto creator) throws BluePurseException {
		return !Objects.isNull(findByIdAndCreator(plannerId, creator));
	}

	@Override
	public List<PurseDto> findByCreator(UserDto user) throws BluePurseException {
		List<PurseEntity> plannerPersistList = plannerDao.findByCreator(ModelObjectMapper.userBean2Persistent(user));
		if (Objects.isNull(plannerPersistList) || plannerPersistList.isEmpty()) {
			String detail = "Can't find planners for user #" + user.getPurser();
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		return ModelObjectMapper.persistentPurse2BeanList(plannerPersistList);
	}

	@Override
	public PurseDto findByIdAndCreator(Long plannerId, UserDto user) throws BluePurseException {
		PurseEntity plannerPersist = plannerDao.findByIdAndCreator(plannerId,
				ModelObjectMapper.userBean2Persistent(user));
		if (Objects.isNull(plannerPersist)) {
			String detail = "Can't find planner with id #" + plannerId + " for user #" + user.getPurser();
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		return ModelObjectMapper.persistentPurse2Bean(plannerPersist);
	}

	@Override
	public PurseDto findPurse(Long plannerId) throws BluePurseException {
		PurseEntity plannerPersistent = plannerDao.find(plannerId);
		if (Objects.isNull(plannerPersistent)) {
			String detail = "Can't find planner with id #" + plannerId;
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		LOGGER.info("get planner by id #" + plannerId);
		return ModelObjectMapper.persistentPurse2Bean(plannerPersistent);
	}

	@Override
	public List<PurseDto> getAllPurse() throws BluePurseException {
		return ModelObjectMapper.persistentPurse2BeanList(plannerDao.findAll());
	}

	@Override
	public PurseDto partialUpdatePurse(Long plannerId, PurseDto plannerToUpdate) throws BluePurseException {
		PurseDto oldPlanner = findPurse(plannerId);
		BluePurseBeanUtil.partialCopyBean(oldPlanner, plannerToUpdate);
		return updatePurse(plannerId, oldPlanner);
	}

	@Override
	public void removePurse(Long plannerId) throws BluePurseException {
		LOGGER.info("remove planner #" + plannerId);
		PurseEntity plannerEntity = plannerDao.find(plannerId);
		// TODO historiser avant de supprimer
		plannerDao.remove(plannerEntity);

	}

	public void setPlannerDao(IPurseDao plannerDao) {
		this.plannerDao = plannerDao;
	}

	@Override
	public PurseDto updatePurse(Long plannerId, PurseDto plannerToUpdate) throws BluePurseException {
		if (Objects.isNull(plannerToUpdate)) {
			String detail = "Can't update entity with null object";
			throw new BluePurseException(BluePurseErrorCode.BP_101, detail);
		}
		plannerToUpdate.setPurseId(plannerId);
		PurseEntity persistentPlanner = plannerDao.merge(ModelObjectMapper.purseBean2Persistent(plannerToUpdate));
		return ModelObjectMapper.persistentPurse2Bean(persistentPlanner);
	}

}
