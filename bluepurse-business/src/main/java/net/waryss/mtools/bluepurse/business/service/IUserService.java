/**
 *
 */
package net.waryss.mtools.bluepurse.business.service;

import java.util.List;

import net.waryss.mtools.bluepurse.dto.PurseDto;
import net.waryss.mtools.bluepurse.dto.ResourceDto;
import net.waryss.mtools.bluepurse.dto.SpendingDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

public interface IUserService {

	/**
	 * Create UserBean
	 *
	 * @param userBean to create
	 * @return user id
	 * @throws BluePurseException
	 *             if data access error
	 */
	UserDto createUser(UserDto userBean) throws BluePurseException;

	/**
	 * @param userId
	 * @param purser
	 * @return
	 * @throws BluePurseException
	 */
	PurseDto createUserPurse(Long userId, PurseDto purseBean) throws BluePurseException;

	/**
	 * @param userId
	 * @param resource
	 * @return
	 * @throws BluePurseException
	 */
	ResourceDto createUserResource(Long userId, ResourceDto resourceBean) throws BluePurseException;

	/**
	 * @param userId
	 * @param spending
	 * @return
	 * @throws BluePurseException
	 */
	SpendingDto createUserSpending(Long userId, SpendingDto spendingBean) throws BluePurseException;

	/**
	 * @param userId
	 * @return
	 */
	boolean existsWithId(Long userId);

	/**
	 * find user by id
	 *
	 * @param Long userId
	 * @return userBean id
	 * @throws BluePurseException
	 */
	UserDto findUser(Long userId) throws BluePurseException;

	/**
	 * @param userId
	 * @param purseId
	 * @return
	 * @throws BluePurseException
	 */
	PurseDto findUserPurse(Long userId, Long purseId) throws BluePurseException;

	/**
	 * @param userId
	 * @return List<PurseDto>
	 * @throws BluePurseException
	 */
	List<PurseDto> findUserPurses(Long userId) throws BluePurseException;

	/**
	 * @param userId
	 * @param resourceId
	 * @return
	 * @throws BluePurseException
	 */
	ResourceDto findUserResource(Long userId, Long presourceId) throws BluePurseException;

	/**
	 * @param userId
	 * @return
	 * @throws BluePurseException
	 */
	List<ResourceDto> findUserResources(Long userId) throws BluePurseException;

	/**
	 * @param userId
	 * @param spendingId
	 * @return
	 * @throws BluePurseException
	 */
	SpendingDto findUserSpending(Long userId, Long spendingId) throws BluePurseException;

	/**
	 * find spendings by user id
	 *
	 * @param Long userId
	 * @return List<SpendingDto>
	 * @throws BluePurseException
	 */
	List<SpendingDto> findUserSpendings(Long userId) throws BluePurseException;

	/**
	 * Get all User in database
	 *
	 * @return users list
	 * @throws BluePurseException if data access error
	 */
	List<UserDto> getAllUser() throws BluePurseException;

	/**
	 * @param userId
	 * @param userBean
	 * @return
	 * @throws BluePurseException
	 */
	UserDto partialUpdateUser(Long userId, UserDto userToUpdate) throws BluePurseException;

	/**
	 * @param userId
	 * @param purseId
	 * @param purse
	 * @return
	 * @throws BluePurseException
	 */
	PurseDto partialUpdateUserPurse(Long userId, Long purseId, PurseDto purseBean) throws BluePurseException;

	/**
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws BluePurseException
	 */
	ResourceDto partialUpdateUserResource(Long userId, Long resourceId, ResourceDto resourceBean) throws BluePurseException;

	/**
	 * @param userId
	 * @param spendingId
	 * @param spending
	 * @return
	 * @throws BluePurseException
	 */
	SpendingDto partialUpdateUserSpending(Long userId, Long spendingId, SpendingDto spendingBean) throws BluePurseException;

	/**
	 * delete User from database
	 *
	 * @param userId
	 * @throws BluePurseException if data access error
	 */
	void removeUser(Long userId) throws BluePurseException;

	/**
	 * @param userId
	 * @param purseId
	 * @throws BluePurseException
	 */
	void removeUserPurse(Long userId, Long purseId) throws BluePurseException;

	/**
	 * @param userId
	 * @param resourceId
	 * @throws BluePurseException
	 */
	void removeUserResource(Long userId, Long resourceId) throws BluePurseException;

	/**
	 * @param userId
	 * @param spendingId
	 * @throws BluePurseException
	 */
	void removeUserSpending(Long userId, Long spendingId) throws BluePurseException;

	/**
	 * Update user
	 * @param userId
	 *
	 * @param userInToUserBean
	 * @return
	 * @throws BluePurseException
	 */
	UserDto updateUser(Long userId, UserDto userToUpdate) throws BluePurseException;

	/**
	 * @param userId
	 * @param purse
	 * @return
	 * @throws BluePurseException
	 */
	PurseDto updateUserPurse(Long userId, Long purseId, PurseDto purseBean) throws BluePurseException;

	/**
	 * @param userId
	 * @param resourceId
	 * @param resource
	 * @return
	 * @throws BluePurseException
	 */
	ResourceDto updateUserResource(Long userId, Long resourceId, ResourceDto resourceBean) throws BluePurseException;

	/**
	 * @param userId
	 * @param spendingId
	 * @param spending
	 * @return
	 * @throws BluePurseException
	 */
	SpendingDto updateUserSpending(Long userId, Long spendingId, SpendingDto spendingBean) throws BluePurseException;

}
