package by.halatsevich.company.entity;

/**
 * The class represents crew data transfer object entity for more convenient use between the layers.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class CrewDto extends Entity {
    private String crewName;
    private int dispatcherId;
    private int numberOfPilots;
    private int numberOfNavigators;
    private int numberOfRadioman;
    private int numberOfStewardesses;
    private Status status;

    /**
     * Instantiates a new Crew dto.
     */
    public CrewDto() {
    }

    /**
     * Instantiates a new Crew dto.
     *
     * @param id                   the id
     * @param crewName             the crew name
     * @param dispatcherId         the dispatcher id
     * @param numberOfPilots       the number of pilots
     * @param numberOfNavigators   the number of navigators
     * @param numberOfRadioman     the number of radioman
     * @param numberOfStewardesses the number of stewardesses
     * @param status               the status
     */
    public CrewDto(int id, String crewName, int dispatcherId, int numberOfPilots, int numberOfNavigators,
                   int numberOfRadioman, int numberOfStewardesses, Status status) {
        super(id);
        this.crewName = crewName;
        this.dispatcherId = dispatcherId;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.status = status;
    }

    /**
     * Instantiates a new Crew dto.
     *
     * @param crewName             the crew name
     * @param dispatcherId         the dispatcher id
     * @param numberOfPilots       the number of pilots
     * @param numberOfNavigators   the number of navigators
     * @param numberOfRadioman     the number of radioman
     * @param numberOfStewardesses the number of stewardesses
     * @param status               the status
     */
    public CrewDto(String crewName, int dispatcherId, int numberOfPilots, int numberOfNavigators, int numberOfRadioman,
                   int numberOfStewardesses, Status status) {

        this.crewName = crewName;
        this.dispatcherId = dispatcherId;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.status = status;
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
     * Gets dispatcher id.
     *
     * @return the dispatcher id
     */
    public int getDispatcherId() {
        return dispatcherId;
    }

    /**
     * Sets dispatcher id.
     *
     * @param dispatcherId the dispatcher id
     */
    public void setDispatcherId(int dispatcherId) {
        this.dispatcherId = dispatcherId;
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

        CrewDto crewDto = (CrewDto) o;

        if (dispatcherId != crewDto.dispatcherId) return false;
        if (numberOfPilots != crewDto.numberOfPilots) return false;
        if (numberOfNavigators != crewDto.numberOfNavigators) return false;
        if (numberOfRadioman != crewDto.numberOfRadioman) return false;
        if (numberOfStewardesses != crewDto.numberOfStewardesses) return false;
        if (crewName != null ? !crewName.equals(crewDto.crewName) : crewDto.crewName != null) return false;
        return status == crewDto.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (crewName != null ? crewName.hashCode() : 0);
        result = 31 * result + dispatcherId;
        result = 31 * result + numberOfPilots;
        result = 31 * result + numberOfNavigators;
        result = 31 * result + numberOfRadioman;
        result = 31 * result + numberOfStewardesses;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CrewDto{");
        sb.append("crewName='").append(crewName).append('\'');
        sb.append(", dispatcherId=").append(dispatcherId);
        sb.append(", numberOfPilots=").append(numberOfPilots);
        sb.append(", numberOfNavigators=").append(numberOfNavigators);
        sb.append(", numberOfRadioman=").append(numberOfRadioman);
        sb.append(", numberOfStewardesses=").append(numberOfStewardesses);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
