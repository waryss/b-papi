/**
 *
 */
package net.waryss.mtools.bluepurse.business.service;

import java.util.List;

import net.waryss.mtools.bluepurse.dto.PurseDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

public interface IPurseService {

	/**
	 * Create purseBean
	 *
	 * @param purseBean to create
	 * @return purse id
	 * @throws BluePurseException if data access error
	 */
	PurseDto createPurse(PurseDto purseBean) throws BluePurseException;

	/**
	 * @param purseId
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	boolean existsWithIdAndCreator(Long purseId, UserDto creator) throws BluePurseException;

	/**
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	List<PurseDto> findByCreator(UserDto user) throws BluePurseException;

	/**
	 * @param purseId
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	PurseDto findByIdAndCreator(Long purseId, UserDto user) throws BluePurseException;

	/**
	 * find purse by id
	 *
	 * @param Long purseId
	 * @return purseBean id
	 * @throws BluePurseException
	 */
	PurseDto findPurse(Long purseId) throws BluePurseException;

	/**
	 * Get all purse in database
	 *
	 * @return purses list
	 * @throws BluePurseException if data access error
	 */
	List<PurseDto> getAllPurse() throws BluePurseException;

	/**
	 * @param purseId
	 * @param purseBean
	 * @return
	 * @throws BluePurseException
	 */
	PurseDto partialUpdatePurse(Long purseId, PurseDto purseToUpdate) throws BluePurseException;

	/**
	 * delete purse from database
	 *
	 * @param purseId
	 * @throws BluePurseException if data access error
	 */
	void removePurse(Long purseId) throws BluePurseException;

	/**
	 * Update purse
	 * @param purseId
	 *
	 * @param purseInTopurseBean
	 * @return purse
	 * @throws BluePurseException
	 */
	PurseDto updatePurse(Long purseId, PurseDto purseToUpdate) throws BluePurseException;

}
