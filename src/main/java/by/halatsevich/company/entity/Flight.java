package by.halatsevich.company.entity;

import java.util.Date;

/**
 * The class represents flight entity.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class Flight extends Entity {
    private Airport departureAirport;
    private Airport destinationAirport;
    private Date departTime;
    private Date arriveTime;
    private Aircraft aircraft;
    private Crew crew;
    private User operator;
    private Status status;

    /**
     * Instantiates a new Flight.
     */
    public Flight() {
    }

    /**
     * Instantiates a new Flight.
     *
     * @param departureAirport   the departure airport
     * @param destinationAirport the destination airport
     * @param departTime         the depart time
     * @param arriveTime         the arrive time
     * @param aircraft           the aircraft
     * @param crew               the crew
     * @param operator           the operator
     * @param status             the status
     */
    public Flight(Airport departureAirport, Airport destinationAirport, Date departTime, Date arriveTime,
                  Aircraft aircraft, Crew crew, User operator, Status status) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.aircraft = aircraft;
        this.crew = crew;
        this.operator = operator;
        this.status = status;
    }

    /**
     * Instantiates a new Flight.
     *
     * @param id                 the id
     * @param departureAirport   the departure airport
     * @param destinationAirport the destination airport
     * @param departTime         the depart time
     * @param arriveTime         the arrive time
     * @param aircraft           the aircraft
     * @param crew               the crew
     * @param operator           the operator
     * @param status             the status
     */
    public Flight(int id, Airport departureAirport, Airport destinationAirport, Date departTime, Date arriveTime,
                  Aircraft aircraft, Crew crew, User operator, Status status) {
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

    /**
     * Gets departure airport.
     *
     * @return the departure airport
     */
    public Airport getDepartureAirport() {
        return departureAirport;
    }

    /**
     * Sets departure airport.
     *
     * @param departureAirport the departure airport
     */
    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    /**
     * Gets destination airport.
     *
     * @return the destination airport
     */
    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * Sets destination airport.
     *
     * @param destinationAirport the destination airport
     */
    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * Gets depart time.
     *
     * @return the depart time
     */
    public Date getDepartTime() {
        return departTime;
    }

    /**
     * Sets depart time.
     *
     * @param departTime the depart time
     */
    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    /**
     * Gets arrive time.
     *
     * @return the arrive time
     */
    public Date getArriveTime() {
        return arriveTime;
    }

    /**
     * Sets arrive time.
     *
     * @param arriveTime the arrive time
     */
    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * Gets aircraft.
     *
     * @return the aircraft
     */
    public Aircraft getAircraft() {
        return aircraft;
    }

    /**
     * Sets aircraft.
     *
     * @param aircraft the aircraft
     */
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    /**
     * Gets crew.
     *
     * @return the crew
     */
    public Crew getCrew() {
        return crew;
    }

    /**
     * Sets crew.
     *
     * @param crew the crew
     */
    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    /**
     * Gets operator.
     *
     * @return the operator
     */
    public User getOperator() {
        return operator;
    }

    /**
     * Sets operator.
     *
     * @param operator the operator
     */
    public void setOperator(User operator) {
        this.operator = operator;
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

        Flight flight = (Flight) o;

        if (departureAirport != null ? !departureAirport.equals(flight.departureAirport) : flight.departureAirport != null)
            return false;
        if (destinationAirport != null ? !destinationAirport.equals(flight.destinationAirport) : flight.destinationAirport != null)
            return false;
        if (departTime != null ? !departTime.equals(flight.departTime) : flight.departTime != null) return false;
        if (arriveTime != null ? !arriveTime.equals(flight.arriveTime) : flight.arriveTime != null) return false;
        if (aircraft != null ? !aircraft.equals(flight.aircraft) : flight.aircraft != null) return false;
        if (crew != null ? !crew.equals(flight.crew) : flight.crew != null) return false;
        if (operator != null ? !operator.equals(flight.operator) : flight.operator != null) return false;
        return status == flight.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (departureAirport != null ? departureAirport.hashCode() : 0);
        result = 31 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
        result = 31 * result + (departTime != null ? departTime.hashCode() : 0);
        result = 31 * result + (arriveTime != null ? arriveTime.hashCode() : 0);
        result = 31 * result + (aircraft != null ? aircraft.hashCode() : 0);
        result = 31 * result + (crew != null ? crew.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
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
