package by.halatsevich.company.entity;

import java.util.Arrays;

public class Aircraft extends Entity{
    private String tailNumber;
    private String aircraftName;
    private AircraftType aircraftType;

    public enum AircraftType{
        CARGO, PASSENGER
    }

    public Aircraft() {
    }

    public Aircraft(String tailNumber, String aircraftName, AircraftType aircraftType) {
        this.tailNumber = tailNumber;
        this.aircraftName = aircraftName;
        this.aircraftType = aircraftType;
    }

    public String getTailNumber() {
        return tailNumber;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public String getAircraftName() {
        return aircraftName;
    }

    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Aircraft aircraft = (Aircraft) o;

        if (!tailNumber.equals(aircraft.tailNumber)) return false;
        if (!aircraftName.equals(aircraft.aircraftName)) return false;
        return aircraftType == aircraft.aircraftType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tailNumber.hashCode();
        result = 31 * result + aircraftName.hashCode();
        result = 31 * result + aircraftType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String aircraft = String.format("Aircraft: tail number - %s, name - %s, type - %s",
                tailNumber,aircraftName,aircraftType.toString().toLowerCase());
        return aircraft;
    }
}

