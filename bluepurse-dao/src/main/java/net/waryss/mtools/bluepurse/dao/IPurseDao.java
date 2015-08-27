package net.waryss.mtools.bluepurse.dao;

import java.util.List;

import net.waryss.mtools.bluepurse.entity.PurseEntity;
import net.waryss.mtools.bluepurse.entity.UserEntity;

public interface IPurseDao extends IGenericDao<PurseEntity, Long> {

	List<PurseEntity> findByCreator(UserEntity user);

	/**
	 * @param id
	 * @param user
	 * @return
	 */
	PurseEntity findByIdAndCreator(Long id, UserEntity user);
}
