package by.halatsevich.company.model.entity;

/**
 * The class represents flight data transfer object entity for more convenient use between the layers.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class FlightDto extends Entity {
    private int departureAirportId;
    private int destinationAirportId;
    private long departTime;
    private long arriveTime;
    private int aircraftId;
    private int crewId;
    private int operatorId;
    private Status status;

    /**
     * Instantiates a new Flight dto.
     */
    public FlightDto() {
    }

    /**
     * Instantiates a new Flight dto.
     *
     * @param id                   the id
     * @param departureAirportId   the departure airport id
     * @param destinationAirportId the destination airport id
     * @param departTime           the depart time
     * @param arriveTime           the arrive time
     * @param aircraftId           the aircraft id
     * @param crewId               the crew id
     * @param operatorId           the operator id
     * @param status               the status
     */
    public FlightDto(int id, int departureAirportId, int destinationAirportId, long departTime, long arriveTime,
                     int aircraftId, int crewId, int operatorId, Status status) {
        super(id);
        this.departureAirportId = departureAirportId;
        this.destinationAirportId = destinationAirportId;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.aircraftId = aircraftId;
        this.crewId = crewId;
        this.operatorId = operatorId;
        this.status = status;
    }

    /**
     * Instantiates a new Flight dto.
     *
     * @param departureAirportId   the departure airport id
     * @param destinationAirportId the destination airport id
     * @param departTime           the depart time
     * @param arriveTime           the arrive time
     * @param aircraftId           the aircraft id
     * @param crewId               the crew id
     * @param operatorId           the operator id
     * @param status               the status
     */
    public FlightDto(int departureAirportId, int destinationAirportId, long departTime, long arriveTime, int aircraftId,
                     int crewId, int operatorId, Status status) {
        this.departureAirportId = departureAirportId;
        this.destinationAirportId = destinationAirportId;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.aircraftId = aircraftId;
        this.crewId = crewId;
        this.operatorId = operatorId;
        this.status = status;
    }

    /**
     * Gets departure airport id.
     *
     * @return the departure airport id
     */
    public int getDepartureAirportId() {
        return departureAirportId;
    }

    /**
     * Sets departure airport id.
     *
     * @param departureAirportId the departure airport id
     */
    public void setDepartureAirportId(int departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    /**
     * Gets destination airport id.
     *
     * @return the destination airport id
     */
    public int getDestinationAirportId() {
        return destinationAirportId;
    }

    /**
     * Sets destination airport id.
     *
     * @param destinationAirportId the destination airport id
     */
    public void setDestinationAirportId(int destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
    }

    /**
     * Gets depart time.
     *
     * @return the depart time
     */
    public long getDepartTime() {
        return departTime;
    }

    /**
     * Sets depart time.
     *
     * @param departTime the depart time
     */
    public void setDepartTime(long departTime) {
        this.departTime = departTime;
    }

    /**
     * Gets arrive time.
     *
     * @return the arrive time
     */
    public long getArriveTime() {
        return arriveTime;
    }

    /**
     * Sets arrive time.
     *
     * @param arriveTime the arrive time
     */
    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * Gets aircraft id.
     *
     * @return the aircraft id
     */
    public int getAircraftId() {
        return aircraftId;
    }

    /**
     * Sets aircraft id.
     *
     * @param aircraftId the aircraft id
     */
    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    /**
     * Gets crew id.
     *
     * @return the crew id
     */
    public int getCrewId() {
        return crewId;
    }

    /**
     * Sets crew id.
     *
     * @param crewId the crew id
     */
    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    /**
     * Gets operator id.
     *
     * @return the operator id
     */
    public int getOperatorId() {
        return operatorId;
    }

    /**
     * Sets operator id.
     *
     * @param operatorId the operator id
     */
    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FlightDto flightDto = (FlightDto) o;

        if (departureAirportId != flightDto.departureAirportId) return false;
        if (destinationAirportId != flightDto.destinationAirportId) return false;
        if (departTime != flightDto.departTime) return false;
        if (arriveTime != flightDto.arriveTime) return false;
        if (aircraftId != flightDto.aircraftId) return false;
        if (crewId != flightDto.crewId) return false;
        if (operatorId != flightDto.operatorId) return false;
        return status == flightDto.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + departureAirportId;
        result = 31 * result + destinationAirportId;
        result = 31 * result + (int) (departTime ^ (departTime >>> 32));
        result = 31 * result + (int) (arriveTime ^ (arriveTime >>> 32));
        result = 31 * result + aircraftId;
        result = 31 * result + crewId;
        result = 31 * result + operatorId;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FlightDto{");
        sb.append("departureAirportId=").append(departureAirportId);
        sb.append(", destinationAirportId=").append(destinationAirportId);
        sb.append(", departTime=").append(departTime);
        sb.append(", arriveTime=").append(arriveTime);
        sb.append(", aircraftId=").append(aircraftId);
        sb.append(", crewId=").append(crewId);
        sb.append(", operatorId=").append(operatorId);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
