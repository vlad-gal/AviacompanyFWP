package by.halatsevich.company.dao;


import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.Entity;

import java.util.List;

public interface BaseDao<T extends Entity> {
    List<T> select() throws DaoException;
    boolean add() throws DaoException;
    T update() throws DaoException;
    boolean delete() throws DaoException;
//
//    default boolean close(){
//
//    }
}
