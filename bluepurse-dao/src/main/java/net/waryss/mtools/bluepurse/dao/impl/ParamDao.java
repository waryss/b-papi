package net.waryss.mtools.bluepurse.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import net.waryss.mtools.bluepurse.dao.IParamDao;
import net.waryss.mtools.bluepurse.entity.param.Param_BucketTypeEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_RecurrencyEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_StatusEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_UserGroupEntity;

@Repository
public class ParamDao extends GenericDao<Object, Long> implements IParamDao {

	@Override
	public Param_BucketTypeEntity findDefaultBucketType() {
		return (Param_BucketTypeEntity) getDefaultFromIndicator(Param_BucketTypeEntity.class);
	}

	@Override
	public Param_RecurrencyEntity findDefaultRecurrence() {
		return (Param_RecurrencyEntity) getDefaultFromIndicator(Param_RecurrencyEntity.class);
	}

	@Override
	public Param_StatusEntity findDefaultStatus() {
		return (Param_StatusEntity) getDefaultFromIndicator(Param_StatusEntity.class);
	}

	@Override
	public Param_UserGroupEntity findDefaultUserGroup() {
		return (Param_UserGroupEntity) getDefaultFromIndicator(Param_UserGroupEntity.class);
	}


	private <T> Object getDefaultFromIndicator(Class<T> clazz){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(clazz);
		Root<T> root = query.from(clazz);
		query.where(builder.equal(root.get("inddefaul"), true));
		return entityManager.createQuery(query).getSingleResult();
	}

}
