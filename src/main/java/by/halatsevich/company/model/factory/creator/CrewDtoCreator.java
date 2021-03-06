package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.entity.CrewDto;
import by.halatsevich.company.entity.Status;
import by.halatsevich.company.model.factory.EntityCreator;

import java.util.Map;

/**
 * The class represents crew dto creator implementation.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CrewDtoCreator implements EntityCreator<CrewDto> {

    @Override
    public CrewDto create(Map<String, Object> crewParameter) {
        int crewId = (int) crewParameter.get(ColumnName.CREW_ID);
        int dispatcherId = (int) crewParameter.get(ColumnName.DISPATCHER_ID);
        int numberOfPilots = (int) crewParameter.get(ColumnName.NUMBER_OF_PILOTS);
        int numberOfNavigators = (int) crewParameter.get(ColumnName.NUMBER_OF_NAVIGATORS);
        int numberOfRadioman = (int) crewParameter.get(ColumnName.NUMBER_OF_RADIOMAN);
        int numberOfStewardesses = (int) crewParameter.get(ColumnName.NUMBER_OF_STEWARDESSES);
        String statusName = (String) crewParameter.get(ColumnName.STATUS_NAME);
        Status status = Status.valueOf(statusName);
        String crewName = (String) crewParameter.get(ColumnName.CREW_NAME);
        return new CrewDto(crewId, crewName, dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, status);
    }
}
