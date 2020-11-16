package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.entity.Aircraft;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.model.factory.EntityCreator;

import java.util.Map;

/**
 * The class represents aircraft creator implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
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
