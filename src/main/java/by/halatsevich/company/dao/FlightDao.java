package by.halatsevich.company.dao;

import by.halatsevich.company.dao.exception.DaoException;
import by.halatsevich.company.entity.Aircraft;

import java.util.List;

public interface FlightDao {
    // добавление удаление обновление получение всех
    List<Aircraft> selectAllAircraft() throws DaoException;
}
