package by.halatsevich.company.dao.impl;

import by.halatsevich.company.dao.CrewDao;
import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.SqlQuery;
import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.dao.factory.EntityFactory;
import by.halatsevich.company.dao.pool.ConnectionPool;
import by.halatsevich.company.entity.Crew;
import by.halatsevich.company.entity.Status;
import org.apache.logging.log4j.Level;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CrewDaoImpl implements CrewDao {

    @Override
    public List<Crew> findAllCrews() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Crew> crews = new ArrayList<>();
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_ALL_CREWS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> crewData = createCrewData(resultSet);
                Crew crew = factory.getCrewCreator().create(crewData);
                logger.log(Level.DEBUG, "Crew found: {}", crew);
                crews.add(crew);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding all crews", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return crews;
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
    public Optional<Crew> findCrewById(int crewId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Crew crew = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_CREW_BY_ID);
            statement.setInt(1, crewId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> crewData = createCrewData(resultSet);
                crew = factory.getCrewCreator().create(crewData);
                logger.log(Level.DEBUG, "Crew found: {}", crew);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding crew by id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(crew);
    }

    @Override
    public Optional<Crew> findCrewByDispatcherId(int dispatcherId) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Crew crew = null;
        EntityFactory factory = EntityFactory.getInstance();
        try {
            statement = connection.prepareStatement(SqlQuery.SELECT_CREW_BY_DISPATCHER_ID);
            statement.setInt(1, dispatcherId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> crewData = createCrewData(resultSet);
                crew = factory.getCrewCreator().create(crewData);
                logger.log(Level.DEBUG, "Crew found: {}", crew);
            }
        } catch (SQLException e) {
            throw new DaoException("Error while finding crew by dispatcher id", e);
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
        }
        return Optional.ofNullable(crew);
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
        }
        return usersId;
    }

    @Override
    public boolean addCrew(Crew crew) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isAdded = false;
        try {
            statement = connection.prepareStatement(SqlQuery.INSERT_CREW);
            statement.setInt(1, crew.getDispatcherId());
            statement.setInt(2, crew.getNumberOfPilots());
            statement.setInt(3, crew.getNumberOfNavigators());
            statement.setInt(4, crew.getNumberOfRadioman());
            statement.setInt(5, crew.getNumberOfStewardesses());
            int statusId = crew.getStatus().ordinal() + 1;
            statement.setInt(6, statusId);
            int add = statement.executeUpdate();
            if (add == 1) {
                isAdded = true;
            }
            logger.log(Level.DEBUG, "Did crew add? {}", isAdded);
        } catch (SQLException e) {
            throw new DaoException("Error while adding crew", e);
        } finally {
            closeStatement(statement);
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
        }
        return isUpdated;
    }

    @Override
    public boolean updateCrew(Crew crew) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement statement = null;
        boolean isUpdated = false;
        try {
            statement = connection.prepareStatement(SqlQuery.UPDATE_CREW);
            statement.setInt(1, crew.getDispatcherId());
            statement.setInt(2, crew.getNumberOfPilots());
            statement.setInt(3, crew.getNumberOfNavigators());
            statement.setInt(4, crew.getNumberOfRadioman());
            statement.setInt(5, crew.getNumberOfStewardesses());
            int statusId = crew.getStatus().ordinal() + 1;
            statement.setInt(6, statusId);
            statement.setInt(7, crew.getId());
            int update = statement.executeUpdate();
            if (update == 1) {
                isUpdated = true;
            }
            logger.log(Level.DEBUG, "Did crew update? {}", isUpdated);
        } catch (SQLException e) {
            throw new DaoException("Error while updating crew", e);
        } finally {
            closeStatement(statement);
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
        }
        return isRemoved;
    }
}