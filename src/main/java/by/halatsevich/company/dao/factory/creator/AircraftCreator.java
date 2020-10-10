package by.halatsevich.company.dao.factory.creator;

import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.factory.EntityCreator;
import by.halatsevich.company.entity.Aircraft;

import java.util.Map;

public class AircraftCreator implements EntityCreator<Aircraft> {

    @Override
    public Aircraft create(Map<String, Object> aircraftParameter) {
        int aircraftId = (int) aircraftParameter.get(SqlColumnName.AIRCRAFT_ID);
        String tailNumber = (String) aircraftParameter.get(SqlColumnName.TAIL_NUMBER);
        String aircraftName = (String) aircraftParameter.get(SqlColumnName.AIRCRAFT_NAME);
        String aircraftTypeName = (String) aircraftParameter.get(SqlColumnName.AIRCRAFT_TYPE);
        Aircraft.AircraftType aircraftType = Aircraft.AircraftType.valueOf(aircraftTypeName);
        return new Aircraft(aircraftId, tailNumber, aircraftName, aircraftType);
    }
}
