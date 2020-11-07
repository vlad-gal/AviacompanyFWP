package by.halatsevich.company.model.entity;

public class Aircraft extends Entity {
    private String tailNumber;
    private String aircraftName;
    private AircraftType aircraftType;
    private Status status;

    public enum AircraftType {
        CARGO("Cargo"),
        PASSENGER("Passenger");

        private String typeName;

        AircraftType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }

    public Aircraft() {
    }

    public Aircraft(String tailNumber, String aircraftName, AircraftType aircraftType, Status status) {
        this.tailNumber = tailNumber;
        this.aircraftName = aircraftName;
        this.aircraftType = aircraftType;
        this.status = status;
    }

    public Aircraft(int id, String tailNumber, String aircraftName, AircraftType aircraftType, Status status) {
        super(id);
        this.tailNumber = tailNumber;
        this.aircraftName = aircraftName;
        this.aircraftType = aircraftType;
        this.status = status;
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

        Aircraft aircraft = (Aircraft) o;

        if (!tailNumber.equals(aircraft.tailNumber)) return false;
        if (!aircraftName.equals(aircraft.aircraftName)) return false;
        if (aircraftType != aircraft.aircraftType) return false;
        return status == aircraft.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + tailNumber.hashCode();
        result = 31 * result + aircraftName.hashCode();
        result = 31 * result + aircraftType.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Aircraft{");
        sb.append("tailNumber='").append(tailNumber).append('\'');
        sb.append(", aircraftName='").append(aircraftName).append('\'');
        sb.append(", aircraftType=").append(aircraftType);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

