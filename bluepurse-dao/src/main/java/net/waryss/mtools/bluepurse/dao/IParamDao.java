
package net.waryss.mtools.bluepurse.dao;

import net.waryss.mtools.bluepurse.entity.param.Param_BucketTypeEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_RecurrencyEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_StatusEntity;
import net.waryss.mtools.bluepurse.entity.param.Param_UserGroupEntity;

public interface IParamDao extends IGenericDao<Object, Long> {

	Param_BucketTypeEntity findDefaultBucketType();

	Param_RecurrencyEntity findDefaultRecurrence();

	Param_StatusEntity findDefaultStatus();

	Param_UserGroupEntity findDefaultUserGroup();
}
