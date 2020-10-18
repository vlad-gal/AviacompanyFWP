package by.halatsevich.company.model.entity;

import java.util.Date;

public class Flight extends Entity {
    private Airport departureAirport;
    private Airport destinationAirport;
    private Date departTime;
    private Date arriveTime;
    private Aircraft aircraft;
    private Crew crew;
    private User operator;
    private Status status;

    public Flight() {
    }

    public Flight(Airport departureAirport, Airport destinationAirport, Date departTime, Date arriveTime, Aircraft aircraft, Crew crew, User operator, Status status) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.aircraft = aircraft;
        this.crew = crew;
        this.operator = operator;
        this.status = status;
    }

    public Flight(int id, Airport departureAirport, Airport destinationAirport, Date departTime, Date arriveTime, Aircraft aircraft, Crew crew, User operator, Status status) {
        super(id);
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.aircraft = aircraft;
        this.crew = crew;
        this.operator = operator;
        this.status = status;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    public User getOperator() {
        return operator;
    }

    public void setOperator(User operator) {
        this.operator = operator;
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

        Flight flight = (Flight) o;

        if (!departureAirport.equals(flight.departureAirport)) return false;
        if (!destinationAirport.equals(flight.destinationAirport)) return false;
        if (!departTime.equals(flight.departTime)) return false;
        if (!arriveTime.equals(flight.arriveTime)) return false;
        if (!aircraft.equals(flight.aircraft)) return false;
        if (!crew.equals(flight.crew)) return false;
        if (!operator.equals(flight.operator)) return false;
        return status == flight.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + departureAirport.hashCode();
        result = 31 * result + destinationAirport.hashCode();
        result = 31 * result + departTime.hashCode();
        result = 31 * result + arriveTime.hashCode();
        result = 31 * result + aircraft.hashCode();
        result = 31 * result + crew.hashCode();
        result = 31 * result + operator.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("departureAirport=").append(departureAirport);
        sb.append(", destinationAirport=").append(destinationAirport);
        sb.append(", departTime=").append(departTime);
        sb.append(", arriveTime=").append(arriveTime);
        sb.append(", aircraft=").append(aircraft);
        sb.append(", crew=").append(crew);
        sb.append(", operator=").append(operator);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
