package by.halatsevich.company.model.dao.impl;

import by.halatsevich.company.model.dao.CrewDao;
import by.halatsevich.company.model.dao.SqlColumnName;
import by.halatsevich.company.model.dao.SqlQuery;
import by.halatsevich.company.model.dao.exception.DaoException;
import by.halatsevich.company.model.factory.EntityFactory;
import by.halatsevich.company.model.dao.pool.ConnectionPool;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CrewDaoImpl implements CrewDao {

    @Override
    public List<CrewDto> findAllCrews() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<CrewDto> crewDtos = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_CREWS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> crewData = createCrewData(resultSet);
                CrewDto crewDto = factory.getCrewDtoCreator().create(crewData);
                logger.log(Level.DEBUG, "CrewDto found: {}", crewDto);
                crewDtos.add(crewDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all crewDtos", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return crewDtos;
    }

    private Map<String, Object> createCrewData(ResultSet resultSet) throws SQLException {
        Map<String, Object> crewData = new HashMap<>();
        crewData.put(SqlColumnName.CREW_ID, resultSet.getInt(1));
        crewData.put(SqlColumnName.DISPATCHER_ID, resultSet.getInt(2));
        crewData.put(SqlColumnName.NUMBER_OF_PILOTS, resultSet.getInt(3));
        crewData.put(SqlColumnName.NUMBER_OF_NAVIGATORS, resultSet.getInt(4));
        crewData.put(SqlColumnName.NUMBER_OF_RADIOMAN, resultSet.getInt(5));
        crewData.put(SqlColumnName.NUMBER_OF_STEWARDESSES, resultSet.getInt(6));
        crewData.put(SqlColumnName.STATUS_NAME, resultSet.getString(7).toUpperCase());
        return crewData;
    }

    @Override
    public Optional<CrewDto> findCrewById(int crewId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CrewDto crewDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_CREW_BY_ID);
            statement.setInt(1, crewId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> crewData = createCrewData(resultSet);
                crewDto = factory.getCrewDtoCreator().create(crewData);
                logger.log(Level.DEBUG, "CrewDto found: {}", crewDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding crewDto by id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(crewDto);
    }

    @Override
    public Optional<CrewDto> findCrewByDispatcherId(int dispatcherId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        CrewDto crewDto = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_CREW_BY_DISPATCHER_ID);
            statement.setInt(1, dispatcherId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> crewData = createCrewData(resultSet);
                crewDto = factory.getCrewDtoCreator().create(crewData);
                logger.log(Level.DEBUG, "CrewDto found: {}", crewDto);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding crewDto by dispatcher id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return Optional.ofNullable(crewDto);
    }

    @Override
    public List<Integer> findUsersIdByCrewId(int crewId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Integer> usersId = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_USERS_ID_BY_CREW_ID);
            statement.setInt(1, crewId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt(1);
                logger.log(Level.DEBUG, "User with id={} found.", userId);
                usersId.add(userId);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding users id in crew", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return usersId;
    }

    @Override
    public boolean addCrew(CrewDto crewDto) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            statement = connection.prepareStatement(SqlQuery.INSERT_CREW);
            statement.setInt(1, crewDto.getDispatcherId());
            statement.setInt(2, crewDto.getNumberOfPilots());
            statement.setInt(3, crewDto.getNumberOfNavigators());
            statement.setInt(4, crewDto.getNumberOfRadioman());
            statement.setInt(5, crewDto.getNumberOfStewardesses());
            int statusId = crewDto.getStatus().ordinal() + 1;
            statement.setInt(6, statusId);
            int add = statement.executeUpdate();
            if (add == 1) {
                isAdded = true;
            }
            logger.log(Level.DEBUG, "Did crewDto add? {}", isAdded);
        } catch (SQLException e) {
            throw new DaoException("Error while adding crewDto", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    @Override
    public boolean addUserIntoCrew(int crewId, int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            statement = connection.prepareStatement(SqlQuery.INSERT_USER_INTO_CREW);
            statement.setInt(1, crewId);
            statement.setInt(2, userId);
            int add = statement.executeUpdate();
            if (add == 1) {
                isAdded = true;
            }
            logger.log(Level.DEBUG, "Did user add into crew? {}", isAdded);
        } catch (SQLException e) {
            throw new DaoException("Error while adding user into crew", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isAdded;
    }

    @Override
    public boolean updateUserIntoCrew(int crewId, int oldUserId, int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_USER_INTO_CREW);
            statement.setInt(1, userId);
            statement.setInt(2, oldUserId);
            statement.setInt(3, crewId);
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did user update into crew? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating user into crew", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean updateDispatcherIntoCrew(int crewId, int oldDispatcherId, int dispatcherId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_DISPATCHER_INTO_CREW);
            statement.setInt(1, dispatcherId);
            statement.setInt(2, oldDispatcherId);
            statement.setInt(3, crewId);
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did dispatcher update into crew? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating dispatcher into crew", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean updateCrew(CrewDto crewDto) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_CREW);
            statement.setInt(1, crewDto.getDispatcherId());
            statement.setInt(2, crewDto.getNumberOfPilots());
            statement.setInt(3, crewDto.getNumberOfNavigators());
            statement.setInt(4, crewDto.getNumberOfRadioman());
            statement.setInt(5, crewDto.getNumberOfStewardesses());
            int statusId = crewDto.getStatus().ordinal() + 1;
            statement.setInt(6, statusId);
            statement.setInt(7, crewDto.getId());
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did crewDto update? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating crewDto", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isUpdated;
    }

    @Override
    public boolean removeCrew(int crewId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isRemoved = false;
        try {
            statement = connection.prepareStatement(SqlQuery.REMOVE_CREW_BY_ID);
            int statusId = Status.INACTIVE.ordinal() + 1;
            statement.setInt(1, statusId);
            statement.setInt(2, crewId);
            int remove = statement.executeUpdate();
            if (remove == 1) {
                isRemoved = true;
            }
            logger.log(Level.DEBUG, "Did crew remove? {}", isRemoved);
        } catch (SQLException e) {
            throw new DaoException("Error while removing crew", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isRemoved;
    }

    @Override
    public boolean removeUserFromCrew(int crewId, int userId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isRemoved = false;
        try {
            statement = connection.prepareStatement(SqlQuery.REMOVE_USER_FROM_CREW_BY_ID);
            statement.setInt(1, userId);
            statement.setInt(2, crewId);
            int remove = statement.executeUpdate();
            if (remove == 1) {
                isRemoved = true;
            }
            logger.log(Level.DEBUG, "Did user remove from crew? {}", isRemoved);
        } catch (SQLException e) {
            throw new DaoException("Error while removing user from crew", e);
        } finally {
            closeStatement(statement);
            pool.releaseConnection(connection);
        }
        return isRemoved;
    }
}