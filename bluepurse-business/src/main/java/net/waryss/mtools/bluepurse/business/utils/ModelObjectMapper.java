package net.waryss.mtools.bluepurse.business.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

import net.waryss.mtools.bluepurse.dto.InfoDto;
import net.waryss.mtools.bluepurse.dto.PurseDto;
import net.waryss.mtools.bluepurse.dto.ResourceDto;
import net.waryss.mtools.bluepurse.dto.SpendingDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.entity.InEntity;
import net.waryss.mtools.bluepurse.entity.OutEntity;
import net.waryss.mtools.bluepurse.entity.PurseEntity;
import net.waryss.mtools.bluepurse.entity.UserEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_InfoEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_StatusEntity;

public class ModelObjectMapper {

	@Autowired
	private static DozerBeanMapper mapper = new DozerBeanMapper();

	static public ResourceDto persistentIn2Bean(InEntity inPersist) {
		return mapper.map(inPersist, ResourceDto.class);
	}

	static public List<ResourceDto> persistentIn2BeanList(List<InEntity> inPersistList) {
		List<ResourceDto> resourceBeanList = null;
		if (!Objects.isNull(inPersistList)) {
			resourceBeanList = new ArrayList<ResourceDto>();
			ResourceDto resourceBean;
			for (InEntity inPersist : inPersistList) {
				resourceBean = persistentIn2Bean(inPersist);
				resourceBeanList.add(resourceBean);
			}
		}
		return resourceBeanList;
	}

	static public InfoDto persistentInfo2Bean(Param_InfoEntity infoPersist) {
		return mapper.map(infoPersist, InfoDto.class);
	}

	static public SpendingDto persistentOut2Bean(OutEntity outPersist) {
		SpendingDto spendingBean = mapper.map(outPersist, SpendingDto.class);
		spendingBean.setSpendingId(outPersist.getUser().getId());
		spendingBean.setRecurrency(outPersist.getRecurrency().getCode());
		return spendingBean;
	}

	static public List<SpendingDto> persistentOut2BeanList(List<OutEntity> outPersistList) {
		List<SpendingDto> spendingBeans = null;
		if (!Objects.isNull(outPersistList)) {
			spendingBeans = new ArrayList<SpendingDto>();
			SpendingDto spendingBean;
			for (OutEntity outPersist : outPersistList) {
				spendingBean = persistentOut2Bean(outPersist);
				spendingBeans.add(spendingBean);
			}
		}
		return spendingBeans;
	}

	static public PurseDto persistentPurse2Bean(PurseEntity pursePersist) {
		PurseDto purseBean = new PurseDto(pursePersist.getId());
		purseBean.setCloseDate(pursePersist.getCloseDate());
		purseBean.setCreationDate(pursePersist.getCreationDate());
		purseBean.setCreator(persistentUser2Bean(pursePersist.getUser()));
		purseBean.setContribution(persistentUser2BeanList(pursePersist.getContribution()));
		return purseBean;
	}

	static public List<PurseDto> persistentPurse2BeanList(List<PurseEntity> pursePersistList) {
		List<PurseDto> purseBeanList = null;
		if (!Objects.isNull(pursePersistList)) {
			purseBeanList = new ArrayList<PurseDto>();
			PurseDto purseBean;
			for (PurseEntity pursePersist : pursePersistList) {
				purseBean = persistentPurse2Bean(pursePersist);
				purseBeanList.add(purseBean);
			}
		}
		return purseBeanList;
	}

	static public UserDto persistentUser2Bean(UserEntity userPersist) {
		UserDto userBean = mapper.map(userPersist, UserDto.class);
		userBean.setStatus(userPersist.getStatus().getCode());
		userBean.setPurser(userPersist.getId());
		return userBean;
	}

	public static List<UserDto> persistentUser2BeanList(List<UserEntity> userPersistList) {
		List<UserDto> userBeans = null;
		if (!Objects.isNull(userPersistList)) {
			userBeans = new ArrayList<UserDto>();
			UserDto userBean;
			for (UserEntity userPersist : userPersistList) {
				userBean = persistentUser2Bean(userPersist);
				userBeans.add(userBean);
			}
		}
		return userBeans;
	}

	static public Set<UserDto> persistentUser2BeanList(Set<UserEntity> userPersistList) {
		Set<UserDto> userBeans = null;
		if (!Objects.isNull(userPersistList)) {
			userBeans = new HashSet<UserDto>();
			UserDto userBean;
			for (UserEntity userPersist : userPersistList) {
				userBean = persistentUser2Bean(userPersist);
				userBeans.add(userBean);
			}
		}
		return userBeans;
	}

	static public PurseEntity purseBean2Persistent(PurseDto purseBean) {
		PurseEntity pursePersist = new PurseEntity();
		pursePersist.setId(purseBean.getPurseId());
		pursePersist.setCloseDate(purseBean.getCloseDate());
		pursePersist.setCreationDate(purseBean.getCreationDate());
		pursePersist.setUser(userBean2Persistent(purseBean.getCreator()));
		pursePersist.setContribution(userBean2PersistentList(purseBean.getContribution()));
		return pursePersist;
	}

	static public InEntity resourceBean2Persistent(ResourceDto resourceBean) {
		return mapper.map(resourceBean, InEntity.class);
	}

	static public OutEntity spending2Persistent(SpendingDto pendingBean) {
		OutEntity outPersist = mapper.map(pendingBean, OutEntity.class);
		UserEntity userPersist = new UserEntity();
		userPersist.setId(pendingBean.getSpendingId());
		outPersist.setUser(userPersist );
		return outPersist;
	}

	static public UserEntity userBean2Persistent(UserDto userBean) {
		UserEntity userPersist = mapper.map(userBean, UserEntity.class);
		userPersist.setId(userBean.getPurser());
		Param_StatusEntity status = new Param_StatusEntity();
		status.setCode(userBean.getStatus());
		userPersist.setStatus(status);
		return userPersist;
	}

	static public Set<UserEntity> userBean2PersistentList(Set<UserDto> userBeans) {
		Set<UserEntity> userPersistList = null;
		if (!Objects.isNull(userBeans)) {
			userPersistList = new HashSet<UserEntity>();
			UserEntity userPersist;
			for (UserDto userBean : userBeans) {
				userPersist = userBean2Persistent(userBean);
				userPersistList.add(userPersist);
			}
		}
		return userPersistList;
	}

	public DozerBeanMapper getMapper() {
		return mapper;
	}

	public void setMapper(DozerBeanMapper mapper) {
		ModelObjectMapper.mapper = mapper;
	}

}
