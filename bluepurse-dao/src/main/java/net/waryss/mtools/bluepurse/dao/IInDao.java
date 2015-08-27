package net.waryss.mtools.bluepurse.dao;

import java.util.List;

import net.waryss.mtools.bluepurse.entity.InEntity;
import net.waryss.mtools.bluepurse.entity.UserEntity;

public interface IInDao extends IGenericDao<InEntity, Long> {

	InEntity findByIdAndUser(Long id, UserEntity user);

	List<InEntity> findByUser(UserEntity user);
}
