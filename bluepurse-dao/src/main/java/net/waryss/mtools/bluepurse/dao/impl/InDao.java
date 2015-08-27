package net.waryss.mtools.bluepurse.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import net.waryss.mtools.bluepurse.dao.IInDao;
import net.waryss.mtools.bluepurse.entity.InEntity;
import net.waryss.mtools.bluepurse.entity.UserEntity;

@Repository
public class InDao extends GenericDao<InEntity, Long>implements IInDao {

	@Override
	public InEntity findByIdAndUser(Long id, UserEntity user) {
		Query query = entityManager.createNamedQuery("findInByIdAndUser", InEntity.class);
		query.setParameter("user", user);
		query.setParameter("id", id);
		return (query.getResultList().isEmpty()) ? null : (InEntity) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InEntity> findByUser(UserEntity user) {
		Query query = entityManager.createNamedQuery("findInsByUser", InEntity.class);
		query.setParameter("user", user);
		return query.getResultList();
	}
}
