package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.entity.CrewDto;

import java.util.List;
import java.util.Optional;

public interface CrewDao extends BaseDao {

    List<CrewDto> findAllCrews() throws DaoException;

    Optional<CrewDto> findCrewById(int crewId) throws DaoException;

    Optional<CrewDto> findCrewByDispatcherId(int dispatcherId) throws DaoException;

    List<Integer> findUsersIdByCrewId(int crewId) throws DaoException;

    boolean addCrew(CrewDto crewDto) throws DaoException;

    boolean addUserIntoCrew(int crewId, int userId) throws DaoException;

    boolean updateUserIntoCrew(int crewId, int oldUserId, int userId) throws DaoException;

    boolean updateDispatcherIntoCrew(int crewId, int oldDispatcherId, int dispatcherId) throws DaoException;

    boolean updateCrew(CrewDto crewDto) throws DaoException;

    boolean removeCrew(int crewId) throws DaoException;

    boolean removeUserFromCrew(int crewId, int userId) throws DaoException;
}