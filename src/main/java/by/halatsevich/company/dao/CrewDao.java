package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.Crew;

import java.util.List;
import java.util.Optional;

public interface CrewDao extends BaseDao {

    List<Crew> findAllCrews() throws DaoException;

    Optional<Crew> findCrewById(int crewId) throws DaoException;

    Optional<Crew> findCrewByDispatcherId(int dispatcherId) throws DaoException;

    List<Integer> findUsersIdByCrewId(int crewId) throws DaoException;

    boolean addCrew(Crew crew) throws DaoException;

    boolean addUserIntoCrew(int crewId, int userId) throws DaoException;

    boolean updateUserIntoCrew(int crewId, int oldUserId, int userId) throws DaoException;

    boolean updateDispatcherIntoCrew(int crewId, int oldDispatcherId, int dispatcherId) throws DaoException;

    boolean updateCrew(Crew crew) throws DaoException;

    boolean removeCrew(int crewId) throws DaoException;

    boolean removeUserFromCrew(int crewId, int userId) throws DaoException;
}
