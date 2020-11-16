package by.halatsevich.company.entity;

import java.util.List;

/**
 * The class represents crew entity.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class Crew extends Entity {
    private User dispatcher;
    private String crewName;
    private List<User> staff;
    private int numberOfPilots;
    private int numberOfNavigators;
    private int numberOfRadioman;
    private int numberOfStewardesses;
    private Status status;

    /**
     * Instantiates a new Crew.
     */
    public Crew() {
    }

    /**
     * Instantiates a new Crew.
     *
     * @param dispatcher           the dispatcher
     * @param crewName             the crew name
     * @param staff                the staff
     * @param numberOfPilots       the number of pilots
     * @param numberOfNavigators   the number of navigators
     * @param numberOfRadioman     the number of radioman
     * @param numberOfStewardesses the number of stewardesses
     * @param status               the status
     */
    public Crew(User dispatcher, String crewName, List<User> staff, int numberOfPilots, int numberOfNavigators,
                int numberOfRadioman, int numberOfStewardesses, Status status) {
        this.dispatcher = dispatcher;
        this.crewName = crewName;
        this.staff = staff;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.status = status;
    }

    /**
     * Instantiates a new Crew.
     *
     * @param id                   the id
     * @param dispatcher           the dispatcher
     * @param crewName             the crew name
     * @param staff                the staff
     * @param numberOfPilots       the number of pilots
     * @param numberOfNavigators   the number of navigators
     * @param numberOfRadioman     the number of radioman
     * @param numberOfStewardesses the number of stewardesses
     * @param status               the status
     */
    public Crew(int id, User dispatcher, String crewName, List<User> staff, int numberOfPilots, int numberOfNavigators,
                int numberOfRadioman, int numberOfStewardesses, Status status) {
        super(id);
        this.dispatcher = dispatcher;
        this.crewName = crewName;
        this.staff = staff;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.status = status;
    }

    /**
     * Gets dispatcher.
     *
     * @return the dispatcher
     */
    public User getDispatcher() {
        return dispatcher;
    }

    /**
     * Sets dispatcher.
     *
     * @param dispatcher the dispatcher
     */
    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Gets crew name.
     *
     * @return the crew name
     */
    public String getCrewName() {
        return crewName;
    }

    /**
     * Sets crew name.
     *
     * @param crewName the crew name
     */
    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    /**
     * Gets staff.
     *
     * @return the staff
     */
    public List<User> getStaff() {
        return staff;
    }

    /**
     * Sets staff.
     *
     * @param staff the staff
     */
    public void setStaff(List<User> staff) {
        this.staff = staff;
    }

    /**
     * Gets number of pilots.
     *
     * @return the number of pilots
     */
    public int getNumberOfPilots() {
        return numberOfPilots;
    }

    /**
     * Sets number of pilots.
     *
     * @param numberOfPilots the number of pilots
     */
    public void setNumberOfPilots(int numberOfPilots) {
        this.numberOfPilots = numberOfPilots;
    }

    /**
     * Gets number of navigators.
     *
     * @return the number of navigators
     */
    public int getNumberOfNavigators() {
        return numberOfNavigators;
    }

    /**
     * Sets number of navigators.
     *
     * @param numberOfNavigators the number of navigators
     */
    public void setNumberOfNavigators(int numberOfNavigators) {
        this.numberOfNavigators = numberOfNavigators;
    }

    /**
     * Gets number of radioman.
     *
     * @return the number of radioman
     */
    public int getNumberOfRadioman() {
        return numberOfRadioman;
    }

    /**
     * Sets number of radioman.
     *
     * @param numberOfRadioman the number of radioman
     */
    public void setNumberOfRadioman(int numberOfRadioman) {
        this.numberOfRadioman = numberOfRadioman;
    }

    /**
     * Gets number of stewardesses.
     *
     * @return the number of stewardesses
     */
    public int getNumberOfStewardesses() {
        return numberOfStewardesses;
    }

    /**
     * Sets number of stewardesses.
     *
     * @param numberOfStewardesses the number of stewardesses
     */
    public void setNumberOfStewardesses(int numberOfStewardesses) {
        this.numberOfStewardesses = numberOfStewardesses;
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

        Crew crew = (Crew) o;

        if (numberOfPilots != crew.numberOfPilots) return false;
        if (numberOfNavigators != crew.numberOfNavigators) return false;
        if (numberOfRadioman != crew.numberOfRadioman) return false;
        if (numberOfStewardesses != crew.numberOfStewardesses) return false;
        if (dispatcher != null ? !dispatcher.equals(crew.dispatcher) : crew.dispatcher != null) return false;
        if (crewName != null ? !crewName.equals(crew.crewName) : crew.crewName != null) return false;
        if (staff != null ? !staff.equals(crew.staff) : crew.staff != null) return false;
        return status == crew.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (dispatcher != null ? dispatcher.hashCode() : 0);
        result = 31 * result + (crewName != null ? crewName.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + numberOfPilots;
        result = 31 * result + numberOfNavigators;
        result = 31 * result + numberOfRadioman;
        result = 31 * result + numberOfStewardesses;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Crew{");
        sb.append("dispatcher=").append(dispatcher);
        sb.append(", crewName='").append(crewName).append('\'');
        sb.append(", staff=").append(staff);
        sb.append(", numberOfPilots=").append(numberOfPilots);
        sb.append(", numberOfNavigators=").append(numberOfNavigators);
        sb.append(", numberOfRadioman=").append(numberOfRadioman);
        sb.append(", numberOfStewardesses=").append(numberOfStewardesses);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
