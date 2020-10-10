package by.halatsevich.company.dao.factory.creator;

import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.factory.EntityCreator;
import by.halatsevich.company.entity.Airport;

import java.util.Map;

public class AirportCreator implements EntityCreator<Airport> {

    @Override
    public Airport create(Map<String, Object> airportParameter) {
        int airportId = (int) airportParameter.get(SqlColumnName.AIRPORT_ID);
        String airportName = (String) airportParameter.get(SqlColumnName.AIRPORT_NAME);
        String country = (String) airportParameter.get(SqlColumnName.COUNTRY);
        String city = (String) airportParameter.get(SqlColumnName.CITY);
        return new Airport(airportId, country, city, airportName);
    }
}
