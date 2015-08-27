package net.waryss.mtools.bluepurse.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 *
 * @param <T>
 * @param <PK>
 */
public interface IGenericDao<T, PK> {
    /**
     * Synchronise entityManager objects in database
     *
     * @throws DataAccessException
     */
    void flush();

    /**
     * Refresh persistent object
     *
     * @param o
     * @throws DataAccessException
     */
    void refresh(T o);

    /**
     * persist an object in database
     *
     * @param o the persistent object to be persisted
     * @return
     * @throws DataAccessException
     */
    void persist(T o);

    /**
     * Remove an object from database
     *
     * @param o the persistent object to be removed
     * @throws DataAccessException
     */
    void remove(T o);

    /**
     * Merge an object from database
     *
     * @param o the persistent object to be merged
     * @throws DataAccessException
     */
    T merge(T o);

    /**
     * load a persistent object with specified id, return null if it does not
     * exist
     *
     * @param l the persistent object id
     * @return T the persistent object found in database
     * @throws DataAccessException
     */
    T find(long l);

    /**
     * load a persistent object with specified id, throw an exception if it does
     * not exist
     *
     * @param userId the persistent object id
     * @return T the persistent object found in database
     * @throws DataAccessException
     */
    T getReference(long userId);

    /**
     * load all persistent objects specified by DAO component
     *
     * @return List list of persistent objects
     * @throws DataAccessException
     */
    List<T> findAll();
}

