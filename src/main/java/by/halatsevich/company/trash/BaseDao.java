//package by.halatsevich.company.dao;
//
//
//import by.halatsevich.company.dao.exception.DaoException;
//import by.halatsevich.company.entity.Entity;
//import org.apache.logging.log4j.Level;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.List;
//
//public interface BaseDao<T extends Entity> {
//    Logger logger = LogManager.getLogger();
//
//    List<T> selectAll() throws DaoException;
//    T select() throws DaoException;
//    boolean add() throws DaoException;
//    T update() throws DaoException;
//    boolean delete() throws DaoException;
//
//
//}
