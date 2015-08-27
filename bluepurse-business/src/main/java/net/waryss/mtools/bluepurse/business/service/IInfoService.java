/**
 *
 */
package net.waryss.mtools.bluepurse.business.service;

import net.waryss.mtools.bluepurse.dto.InfoDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

public interface IInfoService {


	InfoDto ping() throws BluePurseException;

}
