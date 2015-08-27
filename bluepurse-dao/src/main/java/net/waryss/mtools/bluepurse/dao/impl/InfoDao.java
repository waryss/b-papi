package net.waryss.mtools.bluepurse.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import net.waryss.mtools.bluepurse.dao.IInfoDao;
import net.waryss.mtools.bluepurse.entity.param.Param_InfoEntity;

@Repository
public class InfoDao extends GenericDao<Param_InfoEntity, Integer>implements IInfoDao {

	@SuppressWarnings("unchecked")
	@Override
	public Param_InfoEntity findPingInfo() {
		Query query = entityManager.createNamedQuery("findInfoByCode", Param_InfoEntity.class);
		query.setParameter("code", "health");
		List<Param_InfoEntity> result = query.getResultList();
		Param_InfoEntity info = (result.size() > 0) ? result.get(0) : null;
		return info;
	}

}
