package by.halatsevich.company.entity;

public class Crew extends Entity {
    private int dispatcherId;
    private int numberOfPilots;
    private int numberOfNavigators;
    private int numberOfRadioman;
    private int numberOfStewardesses;
    private Status status;

    public Crew() {
    }

    public Crew(int id, int dispatcherId, int numberOfPilots, int numberOfNavigators, int numberOfRadioman, int numberOfStewardesses, Status status) {
        super(id);
        this.dispatcherId = dispatcherId;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.status = status;
    }

    public Crew(int dispatcherId, int numberOfPilots, int numberOfNavigators, int numberOfRadioman, int numberOfStewardesses, Status status) {
        this.dispatcherId = dispatcherId;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.status = status;
    }

    public int getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(int dispatcherId) {
        this.dispatcherId = dispatcherId;
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

        if (dispatcherId != crew.dispatcherId) return false;
        if (numberOfPilots != crew.numberOfPilots) return false;
        if (numberOfNavigators != crew.numberOfNavigators) return false;
        if (numberOfRadioman != crew.numberOfRadioman) return false;
        if (numberOfStewardesses != crew.numberOfStewardesses) return false;
        return status == crew.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + dispatcherId;
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
        sb.append("dispatcherId=").append(dispatcherId);
        sb.append(", numberOfPilots=").append(numberOfPilots);
        sb.append(", numberOfNavigators=").append(numberOfNavigators);
        sb.append(", numberOfRadioman=").append(numberOfRadioman);
        sb.append(", numberOfStewardesses=").append(numberOfStewardesses);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}
