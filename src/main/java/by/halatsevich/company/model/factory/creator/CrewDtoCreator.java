package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.factory.EntityCreator;
import by.halatsevich.company.model.entity.CrewDto;
import by.halatsevich.company.model.entity.Status;

import java.util.Map;

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
        return new CrewDto(crewId, dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, status);
    }
}
