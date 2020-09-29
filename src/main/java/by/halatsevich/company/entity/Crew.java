package by.halatsevich.company.entity;

import java.util.List;

public class Crew extends Entity {
    private User dispatcher;
    private int numberOfPilots;
    private int numberOfNavigators;
    private int numberOfRadioman;
    private int numberOfStewardesses;
    private List<User> crew;

    public Crew() {
    }

    public Crew(long id, User dispatcher, int numberOfPilots, int numberOfNavigators, int numberOfRadioman, int numberOfStewardesses, List<User> crew) {
        super(id);
        this.dispatcher = dispatcher;
        this.numberOfPilots = numberOfPilots;
        this.numberOfNavigators = numberOfNavigators;
        this.numberOfRadioman = numberOfRadioman;
        this.numberOfStewardesses = numberOfStewardesses;
        this.crew = crew;
    }

    public User getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(User dispatcher) {
        this.dispatcher = dispatcher;
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

    public List<User> getCrew() {
        return crew;
    }

    public void setCrew(List<User> crew) {
        this.crew = crew;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Crew crew1 = (Crew) o;

        if (numberOfPilots != crew1.numberOfPilots) return false;
        if (numberOfNavigators != crew1.numberOfNavigators) return false;
        if (numberOfRadioman != crew1.numberOfRadioman) return false;
        if (numberOfStewardesses != crew1.numberOfStewardesses) return false;
        if (!dispatcher.equals(crew1.dispatcher)) return false;
        return crew.equals(crew1.crew);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + dispatcher.hashCode();
        result = 31 * result + numberOfPilots;
        result = 31 * result + numberOfNavigators;
        result = 31 * result + numberOfRadioman;
        result = 31 * result + numberOfStewardesses;
        result = 31 * result + crew.hashCode();
        return result;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Crew{");
        sb.append("dispatcher=").append(dispatcher);
        sb.append(", numberOfPilots=").append(numberOfPilots);
        sb.append(", numberOfNavigators=").append(numberOfNavigators);
        sb.append(", numberOfRadioman=").append(numberOfRadioman);
        sb.append(", numberOfStewardesses=").append(numberOfStewardesses);
        sb.append(", crew=").append(crew);
        sb.append('}');
        return sb.toString();
    }
}
