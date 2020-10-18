package by.halatsevich.company.model.entity;

public class FlightDto extends Entity {
    private int departureAirportId;
    private int destinationAirportId;
    private long departTime;
    private long arriveTime;
    private int aircraftId;
    private int crewId;
    private int operatorId;
    private Status status;

    public FlightDto() {
    }

    public FlightDto(int id, int departureAirportId, int destinationAirportId, long departTime, long arriveTime, int aircraftId, int crewId, int operatorId, Status status) {
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

    public FlightDto(int departureAirportId, int destinationAirportId, long departTime, long arriveTime, int aircraftId, int crewId, int operatorId, Status status) {
        this.departureAirportId = departureAirportId;
        this.destinationAirportId = destinationAirportId;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.aircraftId = aircraftId;
        this.crewId = crewId;
        this.operatorId = operatorId;
        this.status = status;
    }

    public int getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(int departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public int getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(int destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
    }

    public long getDepartTime() {
        return departTime;
    }

    public void setDepartTime(long departTime) {
        this.departTime = departTime;
    }

    public long getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(long arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public int getCrewId() {
        return crewId;
    }

    public void setCrewId(int crewId) {
        this.crewId = crewId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public Status getStatus() {
        return status;
    }

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
        result = 31 * result + status.hashCode();
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