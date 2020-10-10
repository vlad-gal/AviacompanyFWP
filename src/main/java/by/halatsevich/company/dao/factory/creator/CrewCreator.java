package by.halatsevich.company.dao.factory.creator;

import by.halatsevich.company.dao.SqlColumnName;
import by.halatsevich.company.dao.factory.EntityCreator;
import by.halatsevich.company.entity.Crew;
import by.halatsevich.company.entity.Status;

import java.util.Map;

public class CrewCreator implements EntityCreator<Crew> {

    @Override
    public Crew create(Map<String, Object> crewParameter) {
        int crewId = (int) crewParameter.get(SqlColumnName.CREW_ID);
        int dispatcherId = (int) crewParameter.get(SqlColumnName.DISPATCHER_ID);
        int numberOfPilots = (int) crewParameter.get(SqlColumnName.NUMBER_OF_PILOTS);
        int numberOfNavigators = (int) crewParameter.get(SqlColumnName.NUMBER_OF_NAVIGATORS);
        int numberOfRadioman = (int) crewParameter.get(SqlColumnName.NUMBER_OF_RADIOMAN);
        int numberOfStewardesses = (int) crewParameter.get(SqlColumnName.NUMBER_OF_STEWARDESSES);
        String statusName = (String) crewParameter.get(SqlColumnName.STATUS_NAME);
        Status status = Status.valueOf(statusName);
        return new Crew(crewId, dispatcherId, numberOfPilots, numberOfNavigators, numberOfRadioman, numberOfStewardesses, status);
    }
}
