/**
 *
 */
package net.waryss.mtools.bluepurse.business.service;

import java.util.List;

import net.waryss.mtools.bluepurse.dto.SpendingDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;

public interface ISpendingService {

	/**
	 * Create spendingBean
	 *
	 * @param spendingBean
	 *            to create
	 * @return user id
	 * @throws BluePurseException
	 *             if data access error
	 */
	SpendingDto createSpending(SpendingDto spendingBean) throws BluePurseException;

	/**
	 * @param spendingId
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	boolean existsWithIdAndUser(Long spendingId, UserDto findUser) throws BluePurseException;

	/**
	 * @param spendingId
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	SpendingDto findByIdAndUser(Long spendingId, UserDto user) throws BluePurseException;

	/**
	 * @param findUser
	 * @return
	 * @throws BluePurseException
	 */
	List<SpendingDto> findByUser(UserDto user) throws BluePurseException;

	/**
	 * find provision by id
	 *
	 * @param Long
	 *            userId
	 * @return spendingBean id
	 * @throws BluePurseException
	 */
	SpendingDto findSpending(Long spendingId) throws BluePurseException;

	/**
	 * Get all User in database
	 *
	 * @return spendings list
	 * @throws BluePurseException
	 *             if data access error
	 */
	List<SpendingDto> getAllSpending() throws BluePurseException;

	/**
	 * @param spendingId
	 * @param spendingBean
	 * @return
	 * @throws BluePurseException
	 */
	SpendingDto partialUpdateSpending(Long spendingId, SpendingDto spendingToUpdate) throws BluePurseException;

	/**
	 * delete User from database
	 *
	 * @param spendingId
	 * @throws BluePurseException
	 *             if data access error
	 */
	void removeSpending(Long spendingId) throws BluePurseException;

	/**
	 * Update spending
	 * @param spendingId
	 *
	 * @param userInTospendingBean
	 * @return
	 * @throws BluePurseException
	 */
	SpendingDto updateSpending(Long spendingId, SpendingDto spendingToUpdate) throws BluePurseException;

}
