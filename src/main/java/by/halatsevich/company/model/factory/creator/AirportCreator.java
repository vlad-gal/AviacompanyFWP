package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.factory.EntityCreator;
import by.halatsevich.company.model.entity.Airport;

import java.util.Map;

public class AirportCreator implements EntityCreator<Airport> {

    @Override
    public Airport create(Map<String, Object> airportParameter) {
        int airportId = (int) airportParameter.get(ColumnName.AIRPORT_ID);
        String airportName = (String) airportParameter.get(ColumnName.AIRPORT_NAME);
        String country = (String) airportParameter.get(ColumnName.COUNTRY);
        String city = (String) airportParameter.get(ColumnName.CITY);
        return new Airport(airportId, country, city, airportName);
    }
}
