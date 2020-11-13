package by.halatsevich.company.model.dao.impl;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.dao.CrewDao;
import by.halatsevich.company.model.dao.SqlQuery;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.entity.User;
import by.halatsevich.company.model.exception.DaoException;
import by.halatsevich.company.model.factory.EntityFactory;
import by.halatsevich.company.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * The class represents crew dao implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CrewDaoImpl implements CrewDao {

    @Override
    public List<CrewDto> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_CREWS)) {
            ResultSet resultSet = statement.executeQuery();
            return createCrewDtos(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all crews", e);
        }
    }

    @Override
    public List<CrewDto> findAllByStatus(Status status) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_ALL_CREWS_BY_STATUS)) {
            statement.setInt(1, status.ordinal());
            ResultSet resultSet = statement.executeQuery();
            return createCrewDtos(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding all crews by status", e);
        }
    }

    @Override
    public List<User.Role> findUserRolesByCrewId(int crewId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USERS_ROLE_BY_CREW_ID)) {
            statement.setInt(1, crewId);
            ResultSet resultSet = statement.executeQuery();
            List<User.Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                String roleName = resultSet.getString(1);
                roles.add(User.Role.valueOf(roleName.toUpperCase()));
            }
            return roles;
        } catch (SQLException e) {
            throw new DaoException("Error while finding user roles by crew id", e);
        }
    }

    @Override
    public List<CrewDto> findUsersCrewsByStatus(int userId, Status status) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USERS_CREWS_BY_STATUS)) {
            statement.setInt(1, status.ordinal());
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            return createCrewDtos(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding user's crews by status", e);
        }
    }

    @Override
    public Optional<CrewDto> findById(int crewId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_CREW_BY_ID)) {
            statement.setInt(1, crewId);
            ResultSet resultSet = statement.executeQuery();
            return createCrewDto(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding crew by id", e);
        }
    }

    @Override
    public List<Integer> findUserIdsByCrewId(int crewId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_USERS_ID_BY_CREW_ID)) {
            statement.setInt(1, crewId);
            ResultSet resultSet = statement.executeQuery();
            List<Integer> usersId = new ArrayList<>();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                logger.log(Level.DEBUG, "User with id={} found.", userId);
                usersId.add(userId);
            }
            return usersId;
        } catch (SQLException e) {
            throw new DaoException("Error while finding user ids in crew", e);
        }
    }

    @Override
    public Optional<CrewDto> findByCrewName(String crewName) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SELECT_CREW_BY_CREW_NAME)) {
            statement.setString(1, crewName);
            ResultSet resultSet = statement.executeQuery();
            return createCrewDto(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Error while finding crew by name", e);
        }
    }

    @Override
    public boolean addCrew(CrewDto crewDto) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_CREW)) {
            statement.setInt(1, crewDto.getDispatcherId());
            statement.setInt(2, crewDto.getNumberOfPilots());
            statement.setInt(3, crewDto.getNumberOfNavigators());
            statement.setInt(4, crewDto.getNumberOfRadioman());
            statement.setInt(5, crewDto.getNumberOfStewardesses());
            statement.setInt(6, crewDto.getStatus().ordinal());
            statement.setString(7, crewDto.getCrewName());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while adding crew", e);
        }
    }

    @Override
    public boolean addUserIntoCrew(int crewId, int userId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.INSERT_USER_INTO_CREW)) {
            statement.setInt(1, crewId);
            statement.setInt(2, userId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while adding user into crew", e);
        }
    }

    @Override
    public boolean update(CrewDto crewDto) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_CREW)) {
            statement.setInt(1, crewDto.getNumberOfPilots());
            statement.setInt(2, crewDto.getNumberOfNavigators());
            statement.setInt(3, crewDto.getNumberOfRadioman());
            statement.setInt(4, crewDto.getNumberOfStewardesses());
            statement.setInt(5, crewDto.getStatus().ordinal());
            statement.setInt(6, crewDto.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while updating crew", e);
        }
    }

    @Override
    public boolean remove(int crewId) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.REMOVE_CREW_BY_ID)) {
            statement.setInt(1, Status.INACTIVE.ordinal());
            statement.setInt(2, crewId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException("Error while removing crew", e);
        }
    }

    private Map<String, Object> createCrewData(ResultSet resultSet) throws SQLException {
        Map<String, Object> crewData = new HashMap<>();
        crewData.put(ColumnName.CREW_ID, resultSet.getInt(1));
        crewData.put(ColumnName.DISPATCHER_ID, resultSet.getInt(2));
        crewData.put(ColumnName.NUMBER_OF_PILOTS, resultSet.getInt(3));
        crewData.put(ColumnName.NUMBER_OF_NAVIGATORS, resultSet.getInt(4));
        crewData.put(ColumnName.NUMBER_OF_RADIOMAN, resultSet.getInt(5));
        crewData.put(ColumnName.NUMBER_OF_STEWARDESSES, resultSet.getInt(6));
        crewData.put(ColumnName.STATUS_NAME, resultSet.getString(7).toUpperCase());
        crewData.put(ColumnName.CREW_NAME, resultSet.getString(8));
        return crewData;
    }

    private Optional<CrewDto> createCrewDto(ResultSet resultSet) throws SQLException {
        CrewDto crewDto = null;
        while (resultSet.next()) {
            Map<String, Object> crewData = createCrewData(resultSet);
            crewDto = EntityFactory.getInstance().getCrewDtoCreator().create(crewData);
            logger.log(Level.DEBUG, "Crew found: {}", crewDto);
        }
        return Optional.ofNullable(crewDto);
    }

    private List<CrewDto> createCrewDtos(ResultSet resultSet) throws SQLException {
        List<CrewDto> crewDtos = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        while (resultSet.next()) {
            Map<String, Object> crewData = createCrewData(resultSet);
            CrewDto crewDto = factory.getCrewDtoCreator().create(crewData);
            logger.log(Level.DEBUG, "Crew found: {}", crewDto);
            crewDtos.add(crewDto);
        }
        return crewDtos;
    }
}