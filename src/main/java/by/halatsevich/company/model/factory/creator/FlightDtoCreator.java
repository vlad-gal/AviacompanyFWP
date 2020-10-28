package by.halatsevich.company.model.factory.creator;

import by.halatsevich.company.model.dao.ColumnName;
import by.halatsevich.company.model.factory.EntityCreator;
import by.halatsevich.company.model.entity.FlightDto;
import by.halatsevich.company.model.entity.Status;

import java.util.Map;

public class FlightDtoCreator implements EntityCreator<FlightDto> {

    @Override
    public FlightDto create(Map<String, Object> flightParameter) {
        int flightId = (int) flightParameter.get(ColumnName.FLIGHT_ID);
        int departureAirportId = (int) flightParameter.get(ColumnName.DEPARTURE_AIRPORT_ID);
        int destinationAirportId = (int) flightParameter.get(ColumnName.DESTINATION_AIRPORT_ID);
        long departTime = (long) flightParameter.get(ColumnName.DEPART_TIME);
        long arriveTime = (long) flightParameter.get(ColumnName.ARRIVE_TIME);
        int aircraftId = (int) flightParameter.get(ColumnName.AIRCRAFT_ID);
        int crewId = (int) flightParameter.get(ColumnName.CREW_ID);
        int operatorId = (int) flightParameter.get(ColumnName.OPERATOR_ID);
        String statusName = (String) flightParameter.get(ColumnName.STATUS_NAME);
        Status status = Status.valueOf(statusName);
        return new FlightDto(flightId, departureAirportId, destinationAirportId, departTime, arriveTime, aircraftId, crewId, operatorId, status);
    }
}