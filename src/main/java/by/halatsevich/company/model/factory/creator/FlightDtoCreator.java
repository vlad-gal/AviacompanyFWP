package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.SqlColumnName;
import by.halatsevich.company.model.entity.Flight;
import by.halatsevich.company.model.factory.EntityCreator;
import by.halatsevich.company.model.entity.FlightDto;
import by.halatsevich.company.model.entity.Status;

import java.util.Map;

public class FlightDtoCreator implements EntityCreator<FlightDto> {

    @Override
    public FlightDto create(Map<String, Object> flightParameter) {
        int flightId = (int) flightParameter.get(SqlColumnName.FLIGHT_ID);
        int departureAirportId = (int) flightParameter.get(SqlColumnName.DEPARTURE_AIRPORT_ID);
        int destinationAirportId = (int) flightParameter.get(SqlColumnName.DESTINATION_AIRPORT_ID);
        long departTime = (long) flightParameter.get(SqlColumnName.DEPART_TIME);
        long arriveTime = (long) flightParameter.get(SqlColumnName.ARRIVE_TIME);
        int aircraftId = (int) flightParameter.get(SqlColumnName.AIRCRAFT_ID);
        int crewId = (int) flightParameter.get(SqlColumnName.CREW_ID);
        int operatorId = (int) flightParameter.get(SqlColumnName.OPERATOR_ID);
        String statusName = (String) flightParameter.get(SqlColumnName.STATUS_NAME);
        Status status = Status.valueOf(statusName);
        return new FlightDto(flightId, departureAirportId, destinationAirportId, departTime, arriveTime, aircraftId, crewId, operatorId, status);
    }
}