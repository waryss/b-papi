/**
 *
 */
package net.waryss.mtools.bluepurse.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import net.waryss.mtools.bluepurse.dao.IPurseDao;
import net.waryss.mtools.bluepurse.entity.PurseEntity;
import net.waryss.mtools.bluepurse.entity.UserEntity;

@Repository
public class PurseDao extends GenericDao<PurseEntity, Long>implements IPurseDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<PurseEntity> findByCreator(UserEntity user) {
		Query query = entityManager.createNamedQuery("findPurseByCreator", PurseEntity.class);
		query.setParameter("user", user);
		return query.getResultList();
	}

	@Override
	public PurseEntity findByIdAndCreator(Long id, UserEntity user) {
		Query query = entityManager.createNamedQuery("findPurseByCreator", PurseEntity.class);
		query.setParameter("user", user);
		query.setParameter("id", id);
		return (query.getResultList().isEmpty()) ? null : (PurseEntity) query.getResultList().get(0);
	}
}
