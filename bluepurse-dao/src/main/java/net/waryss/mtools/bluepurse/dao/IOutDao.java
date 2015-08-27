package net.waryss.mtools.bluepurse.dao;

import java.util.List;

import net.waryss.mtools.bluepurse.entity.OutEntity;
import net.waryss.mtools.bluepurse.entity.UserEntity;

public interface IOutDao extends IGenericDao<OutEntity, Long> {

	OutEntity findByIdAndUser(Long id, UserEntity user);

	List<OutEntity> findByUser(UserEntity user);
}
