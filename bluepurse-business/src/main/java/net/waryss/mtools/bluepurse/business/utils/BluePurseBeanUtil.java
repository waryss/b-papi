package net.waryss.mtools.bluepurse.business.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.waryss.mtools.bluepurse.dto.BluePurseBean;
import net.waryss.mtools.bluepurse.exception.BluePurseErrorCode;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

public class BluePurseBeanUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(BluePurseBeanUtil.class);

	/**
	 * Permet de faire la copie partielle des proprietes d'un objet (orig) dans
	 * une autre (dest)
	 *
	 * @param dest
	 * @param orig
	 * @throws BluePurseException
	 */

	public static void partialCopyBean(Object dest, Object orig) throws BluePurseException {

		try {
			for (String key : BeanUtils.describe(orig).keySet()) {
				if (!"class".equals(key)) {
					Object value = PropertyUtils.getProperty(orig, key);
					if (!Objects.isNull(value)) {
						if (value instanceof BluePurseBean) {
							partialCopyBean(PropertyUtils.getProperty(dest, key), value);
						} else {
							BeanUtils.copyProperty(dest, key, value);
						}
					}
				}
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			String message = e.getMessage();
			LOGGER.error(message);
			throw new BluePurseException(BluePurseErrorCode.BP_10, message);
		}
	}
}
