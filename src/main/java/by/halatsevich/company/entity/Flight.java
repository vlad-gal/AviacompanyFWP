package by.halatsevich.company.entity;

public class Flight extends Entity {
    private Airport departure;
    private Airport destination;
    private long departTime;
    private long arriveTime;
    private Aircraft aircraft;
    private Crew crew;
    private User admin;

    public Flight() {
    }

    public Flight(long id, Airport departure, Airport destination, long departTime, long arriveTime, Aircraft aircraft, Crew crew, User admin) {
        super(id);
        this.departure = departure;
        this.destination = destination;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.aircraft = aircraft;
        this.crew = crew;
        this.admin = admin;
    }

    public Airport getDeparture() {

        return departure;
    }

    public void setDeparture(Airport departure) {
        this.departure = departure;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
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

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Flight flight = (Flight) o;

        if (departTime != flight.departTime) return false;
        if (arriveTime != flight.arriveTime) return false;
        if (!departure.equals(flight.departure)) return false;
        if (!destination.equals(flight.destination)) return false;
        if (!aircraft.equals(flight.aircraft)) return false;
        if (!crew.equals(flight.crew)) return false;
        return admin.equals(flight.admin);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + departure.hashCode();
        result = 31 * result + destination.hashCode();
        result = 31 * result + (int) (departTime ^ (departTime >>> 32));
        result = 31 * result + (int) (arriveTime ^ (arriveTime >>> 32));
        result = 31 * result + aircraft.hashCode();
        result = 31 * result + crew.hashCode();
        result = 31 * result + admin.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("departure=").append(departure);
        sb.append(", destination=").append(destination);
        sb.append(", departTime=").append(departTime);
        sb.append(", arriveTime=").append(arriveTime);
        sb.append(", aircraft=").append(aircraft);
        sb.append(", crew=").append(crew);
        sb.append(", admin=").append(admin);
        sb.append('}');
        return sb.toString();
    }
}
