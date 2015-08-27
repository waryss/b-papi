package net.waryss.mtools.bluepurse.dao.impl;

import org.springframework.stereotype.Repository;

import net.waryss.mtools.bluepurse.dao.IUserDao;
import net.waryss.mtools.bluepurse.entity.UserEntity;

@Repository
public class UserDao extends GenericDao<UserEntity, Long>implements IUserDao {
}
