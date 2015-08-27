package net.waryss.mtools.bluepurse.business.service.impl;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.waryss.mtools.bluepurse.business.service.IInfoService;
import net.waryss.mtools.bluepurse.business.utils.ModelObjectMapper;
import net.waryss.mtools.bluepurse.dao.IInfoDao;
import net.waryss.mtools.bluepurse.dto.InfoDto;
import net.waryss.mtools.bluepurse.entity.param.Param_InfoEntity;
import net.waryss.mtools.bluepurse.exception.BluePurseErrorCode;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

@Service
public class InfoService implements IInfoService {

	private static Logger LOGGER = LoggerFactory.getLogger(InfoService.class);

	@Autowired
	private IInfoDao infoDao;

	@Override
	public InfoDto ping() throws BluePurseException {
		LOGGER.info("getting ping info" + infoDao.findPingInfo());
		Param_InfoEntity persistentInfo = infoDao.findPingInfo();
		if (Objects.isNull(persistentInfo)) {
			String detail = "Can't retrieve health info";
			throw new BluePurseException(BluePurseErrorCode.BP_100, detail);
		}
		return ModelObjectMapper.persistentInfo2Bean(infoDao.findPingInfo());
	}

	public IInfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(IInfoDao infoDao) {
		this.infoDao = infoDao;
	}

}
