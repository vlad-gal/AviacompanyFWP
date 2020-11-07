package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.entity.Aircraft;
import by.halatsevich.company.model.entity.Status;
import by.halatsevich.company.model.factory.EntityCreator;

import java.util.Map;

public class AircraftCreator implements EntityCreator<Aircraft> {

    @Override
    public Aircraft create(Map<String, Object> aircraftParameter) {
        int aircraftId = (int) aircraftParameter.get(ColumnName.AIRCRAFT_ID);
        String tailNumber = (String) aircraftParameter.get(ColumnName.TAIL_NUMBER);
        String aircraftName = (String) aircraftParameter.get(ColumnName.AIRCRAFT_NAME);
        String aircraftTypeName = (String) aircraftParameter.get(ColumnName.AIRCRAFT_TYPE);
        Aircraft.AircraftType aircraftType = Aircraft.AircraftType.valueOf(aircraftTypeName);
        String aircraftStatusName = (String) aircraftParameter.get(ColumnName.STATUS_NAME);
        Status aircraftStatus = Status.valueOf(aircraftStatusName);
        return new Aircraft(aircraftId, tailNumber, aircraftName, aircraftType, aircraftStatus);
    }
}
