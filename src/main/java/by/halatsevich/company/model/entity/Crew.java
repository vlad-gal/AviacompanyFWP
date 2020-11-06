package by.halatsevich.company.model.entity;

import java.util.List;

public class Crew extends Entity {
    private User dispatcher;
    private String crewName;
    private List<User> staff;
    private int numberOfPilots;
    private int numberOfNavigators;
    private int numberOfRadioman;
    private int numberOfStewardesses;
    private Status status;

    public Crew() {
    }

    public Crew(User dispatcher, String crewName, List<User> staff, int numberOfPilots, int numberOfNavigators, int numberOfRadioman, int numberOfStewardesses, Status status) {

        this.dispatcher = dispatcher;
        this.crewName = crewName;
        this.staff = staff;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.status = status;
    }

    public Crew(int id, User dispatcher, String crewName, List<User> staff, int numberOfPilots, int numberOfNavigators, int numberOfRadioman, int numberOfStewardesses, Status status) {
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

    public User getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public List<User> getStaff() {
        return staff;
    }

    public void setStaff(List<User> staff) {
        this.staff = staff;
    }

    public int getNumberOfPilots() {
        return numberOfPilots;
    }

    public void setNumberOfPilots(int numberOfPilots) {
        this.numberOfPilots = numberOfPilots;
    }

    public int getNumberOfNavigators() {
        return numberOfNavigators;
    }

    public void setNumberOfNavigators(int numberOfNavigators) {
        this.numberOfNavigators = numberOfNavigators;
    }

    public int getNumberOfRadioman() {
        return numberOfRadioman;
    }

    public void setNumberOfRadioman(int numberOfRadioman) {
        this.numberOfRadioman = numberOfRadioman;
    }

    public int getNumberOfStewardesses() {
        return numberOfStewardesses;
    }

    public void setNumberOfStewardesses(int numberOfStewardesses) {
        this.numberOfStewardesses = numberOfStewardesses;
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

        Crew crew = (Crew) o;

        if (numberOfPilots != crew.numberOfPilots) return false;
        if (numberOfNavigators != crew.numberOfNavigators) return false;
        if (numberOfRadioman != crew.numberOfRadioman) return false;
        if (numberOfStewardesses != crew.numberOfStewardesses) return false;
        if (!dispatcher.equals(crew.dispatcher)) return false;
        if (!crewName.equals(crew.crewName)) return false;
        if (!staff.equals(crew.staff)) return false;
        return status == crew.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + dispatcher.hashCode();
        result = 31 * result + crewName.hashCode();
        result = 31 * result + staff.hashCode();
        result = 31 * result + numberOfPilots;
        result = 31 * result + numberOfNavigators;
        result = 31 * result + numberOfRadioman;
        result = 31 * result + numberOfStewardesses;
        result = 31 * result + status.hashCode();
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
