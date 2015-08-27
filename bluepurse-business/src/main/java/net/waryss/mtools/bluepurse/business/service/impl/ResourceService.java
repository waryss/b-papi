package net.waryss.mtools.bluepurse.business.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.waryss.mtools.bluepurse.business.service.IResourceService;
import net.waryss.mtools.bluepurse.business.utils.BluePurseBeanUtil;
import net.waryss.mtools.bluepurse.business.utils.ModelObjectMapper;
import net.waryss.mtools.bluepurse.dao.IInDao;
import net.waryss.mtools.bluepurse.dto.ResourceDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.entity.InEntity;
import net.waryss.mtools.bluepurse.exception.BluePurseErrorCode;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

@Service
public class ResourceService implements IResourceService {

	private static Logger LOGGER = LoggerFactory.getLogger(ResourceService.class);
	@Autowired
	private IInDao inDao;

	@Override
	public ResourceDto createResource(ResourceDto resourceBean) throws BluePurseException {
		InEntity inPersistent = ModelObjectMapper.resourceBean2Persistent(resourceBean);
		inDao.persist(inPersistent);
		LOGGER.info("return created resource #" + inPersistent.getId());
		return ModelObjectMapper.persistentIn2Bean(inPersistent);
	}

	@Override
	public boolean existsResourceWithIdAndUser(Long resourceId, UserDto user) throws BluePurseException {
		return !Objects.isNull(findByIdAndUser(resourceId, user));
	}

	@Override
	public ResourceDto findByIdAndUser(Long resourceId, UserDto user) throws BluePurseException {
		InEntity provisionPersist = inDao.findByIdAndUser(resourceId,
				ModelObjectMapper.userBean2Persistent(user));
		if (Objects.isNull(provisionPersist)) {
			String detail = "Can't find provision with id #" + resourceId + " for user #" + user.getPurser();
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		return ModelObjectMapper.persistentIn2Bean(provisionPersist);
	}

	@Override
	public List<ResourceDto> findByUser(UserDto user) throws BluePurseException {
		List<InEntity> provisionPersistList = inDao
				.findByUser(ModelObjectMapper.userBean2Persistent(user));
		if (Objects.isNull(provisionPersistList) || provisionPersistList.isEmpty()) {
			String detail = "Can't find provisions for user #" + user.getPurser();
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		return ModelObjectMapper.persistentIn2BeanList(provisionPersistList);
	}

	@Override
	public ResourceDto findResource(Long resourceId) throws BluePurseException {
		InEntity inPersistent = inDao.find(resourceId);
		if (Objects.isNull(inPersistent)) {
			String detail = "Can't find provision with id #" + resourceId;
			throw new BluePurseException(BluePurseErrorCode.BP_102, detail);
		}
		LOGGER.info("get provision by id #" + resourceId);
		return ModelObjectMapper.persistentIn2Bean(inPersistent);
	}

	@Override
	public List<ResourceDto> getAllResource() throws BluePurseException {
		return ModelObjectMapper.persistentIn2BeanList(inDao.findAll());
	}

	@Override
	public ResourceDto partialUpdateResource(Long resourceId, ResourceDto provisionToUpdate) throws BluePurseException {
		ResourceDto oldProvision = findResource(resourceId);
		BluePurseBeanUtil.partialCopyBean(oldProvision, provisionToUpdate);
		return updateResource(resourceId, oldProvision);
	}

	@Override
	public void removeResource(Long resourceId) throws BluePurseException {
		LOGGER.info("remove provision #" + resourceId);
		InEntity InEntity = inDao.find(resourceId);
		// TODO historiser avant de supprimer
		inDao.remove(InEntity);

	}

	public void setProvisionDao(IInDao provisionDao) {
		inDao = provisionDao;
	}

	@Override
	public ResourceDto updateResource(Long resourceId, ResourceDto provisionToUpdate) throws BluePurseException {
		if (Objects.isNull(provisionToUpdate)) {
			String detail = "Can't update entity with null object";
			throw new BluePurseException(BluePurseErrorCode.BP_101, detail);
		}
		provisionToUpdate.setResourceId(resourceId);
		InEntity persistentProvision = inDao
				.merge(ModelObjectMapper.resourceBean2Persistent(provisionToUpdate));
		return ModelObjectMapper.persistentIn2Bean(persistentProvision);
	}

}
