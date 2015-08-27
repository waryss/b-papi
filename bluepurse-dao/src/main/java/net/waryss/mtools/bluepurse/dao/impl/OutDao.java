package net.waryss.mtools.bluepurse.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import net.waryss.mtools.bluepurse.dao.IOutDao;
import net.waryss.mtools.bluepurse.entity.OutEntity;
import net.waryss.mtools.bluepurse.entity.UserEntity;

@Repository
public class OutDao extends GenericDao<OutEntity, Long> implements IOutDao {

	@Override
	public OutEntity findByIdAndUser(Long id, UserEntity user) {
		Query query = entityManager.createNamedQuery("findOutsByUser", OutEntity.class);
		query.setParameter("user", user);
		query.setParameter("id", id);
		return (query.getResultList().isEmpty()) ? null : (OutEntity) query.getResultList().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OutEntity> findByUser(UserEntity user) {
		Query query = entityManager.createNamedQuery("findOutsByUser", OutEntity.class);
		query.setParameter("user", user);
		return query.getResultList();
	}
}
