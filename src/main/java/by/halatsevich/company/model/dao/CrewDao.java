package by.halatsevich.company.model.dao;

import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.entity.CrewDto;

import java.util.List;
import java.util.Optional;

public interface CrewDao extends BaseDao<CrewDto> {

    Optional<CrewDto> findCrewByDispatcherId(int dispatcherId) throws DaoException;

    List<Integer> findUsersIdByCrewId(int crewId) throws DaoException;

    boolean addCrew(CrewDto crewDto) throws DaoException;

    boolean addUserIntoCrew(int crewId, int userId) throws DaoException;

    boolean updateUserIntoCrew(int crewId, int oldUserId, int userId) throws DaoException;

    boolean updateDispatcherIntoCrew(int crewId, int oldDispatcherId, int dispatcherId) throws DaoException;

    boolean removeUserFromCrew(int crewId, int userId) throws DaoException;

    Optional<CrewDto> findByCrewName(String crewName) throws DaoException;

    List<User.Role> findUsersRoleByCrewId(int crewId) throws DaoException;

    List<CrewDto> findUsersCrewsByStatus(int userId, Status status) throws DaoException;
}
