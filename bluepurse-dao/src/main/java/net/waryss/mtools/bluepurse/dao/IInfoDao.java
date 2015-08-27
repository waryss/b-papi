package net.waryss.mtools.bluepurse.dao;

import net.waryss.mtools.bluepurse.entity.param.Param_InfoEntity;

public interface IInfoDao extends IGenericDao<Param_InfoEntity, Integer> {

	Param_InfoEntity findPingInfo();
}
