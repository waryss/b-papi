/**
 *
 */
package net.waryss.mtools.bluepurse.business.service;

import java.util.List;

import net.waryss.mtools.bluepurse.dto.ResourceDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

public interface IResourceService {

	/**
	 * Create resourceBean
	 *
	 * @param resourceBean
	 *            to create
	 * @return resource id
	 * @throws BluePurseException
	 *             if data access error
	 */
	ResourceDto createResource(ResourceDto resourceBean) throws BluePurseException;

	/**
	 * @param resourceId
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	boolean existsResourceWithIdAndUser(Long resourceId, UserDto user) throws BluePurseException;

	/**
	 * @param resourceId
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	ResourceDto findByIdAndUser(Long resourceId, UserDto user) throws BluePurseException;

	/**
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	List<ResourceDto> findByUser(UserDto user) throws BluePurseException;

	/**
	 * find resource by id
	 *
	 * @param Long
	 *            resourceId
	 * @return resourceBean id
	 * @throws BluePurseException
	 */
	ResourceDto findResource(Long resourceId) throws BluePurseException;

	/**
	 * Get all User in database
	 *
	 * @return resources list
	 * @throws BluePurseException
	 *             if data access error
	 */
	List<ResourceDto> getAllResource() throws BluePurseException;

	/**
	 * @param resourceId
	 * @param resourceBean
	 * @return
	 * @throws BluePurseException
	 */
	ResourceDto partialUpdateResource(Long resourceId, ResourceDto resourceToUpdate) throws BluePurseException;

	/**
	 * delete User from database
	 *
	 * @param resourceId
	 * @throws BluePurseException
	 *             if data access error
	 */
	void removeResource(Long resourceId) throws BluePurseException;

	/**
	 * Update resource
	 * @param resourceId
	 *
	 * @param resourceInToresourceBean
	 * @return
	 * @throws BluePurseException
	 */
	ResourceDto updateResource(Long resourceId, ResourceDto resourceToUpdate) throws BluePurseException;
}
