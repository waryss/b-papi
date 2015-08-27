package net.waryss.mtools.bluepurse.business.utils;

import java.sql.Date;
import java.time.LocalDate;

public class BluePurseDateUtil {

	public static Date now() {
		return Date.valueOf(LocalDate.now());
	}
}