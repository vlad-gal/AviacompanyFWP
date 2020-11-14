package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * The interface represents crew dao.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public interface CrewDao extends BaseDao<CrewDto> {

    /**
     * Add crew dto.
     *
     * @param crewDto the crew dto
     * @return true if adding successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean addCrew(CrewDto crewDto) throws DaoException;

    /**
     * Add user into crew.
     *
     * @param crewId the crew id
     * @param userId the user id
     * @return true if adding successful, otherwise false
     * @throws DaoException the dao exception
     */
    boolean addUserIntoCrew(int crewId, int userId) throws DaoException;

    /**
     * Find user ids by crew id.
     *
     * @param crewId the crew id
     * @return the list of user ids
     * @throws DaoException the dao exception
     */
    List<Integer> findUserIdsByCrewId(int crewId) throws DaoException;

    /**
     * Find crew dto by name.
     *
     * @param crewName the crew name
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<CrewDto> findByCrewName(String crewName) throws DaoException;

    /**
     * Find user roles by crew id.
     *
     * @param crewId the crew id
     * @return the list of user roles
     * @throws DaoException the dao exception
     */
    List<User.Role> findUserRolesByCrewId(int crewId) throws DaoException;

    /**
     * Find user's crews by status.
     *
     * @param userId the user id
     * @param status the status
     * @return the list of user's crews
     * @throws DaoException the dao exception
     */
    List<CrewDto> findUserCrewsByStatus(int userId, Status status) throws DaoException;
}
