package by.halatsevich.company.model.entity;

/**
 * The class represents aircraft entity.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class Aircraft extends Entity {
    private String tailNumber;
    private String aircraftName;
    private AircraftType aircraftType;
    private Status status;

    /**
     * The enum Aircraft type.
     */
    public enum AircraftType {
        /**
         * Cargo aircraft type.
         */
        CARGO("Cargo"),
        /**
         * Passenger aircraft type.
         */
        PASSENGER("Passenger");

        private String typeName;

        AircraftType(String typeName) {
            this.typeName = typeName;
        }

        /**
         * Gets type name.
         *
         * @return the type name
         */
        public String getTypeName() {
            return typeName;
        }
    }

    /**
     * Instantiates a new Aircraft.
     */
    public Aircraft() {
    }

    /**
     * Instantiates a new Aircraft.
     *
     * @param tailNumber   the tail number
     * @param aircraftName the aircraft name
     * @param aircraftType the aircraft type
     * @param status       the status
     */
    public Aircraft(String tailNumber, String aircraftName, AircraftType aircraftType, Status status) {
        this.tailNumber = tailNumber;
        this.aircraftName = aircraftName;
        this.aircraftType = aircraftType;
        this.status = status;
    }

    /**
     * Instantiates a new Aircraft.
     *
     * @param id           the id
     * @param tailNumber   the tail number
     * @param aircraftName the aircraft name
     * @param aircraftType the aircraft type
     * @param status       the status
     */
    public Aircraft(int id, String tailNumber, String aircraftName, AircraftType aircraftType, Status status) {
        super(id);
        this.tailNumber = tailNumber;
        this.aircraftName = aircraftName;
        this.aircraftType = aircraftType;
        this.status = status;
    }

    /**
     * Gets tail number.
     *
     * @return the tail number
     */
    public String getTailNumber() {
        return tailNumber;
    }

    /**
     * Sets tail number.
     *
     * @param tailNumber the tail number
     */
    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    /**
     * Gets aircraft name.
     *
     * @return the aircraft name
     */
    public String getAircraftName() {
        return aircraftName;
    }

    /**
     * Sets aircraft name.
     *
     * @param aircraftName the aircraft name
     */
    public void setAircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    /**
     * Gets aircraft type.
     *
     * @return the aircraft type
     */
    public AircraftType getAircraftType() {
        return aircraftType;
    }

    /**
     * Sets aircraft type.
     *
     * @param aircraftType the aircraft type
     */
    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
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

        Aircraft aircraft = (Aircraft) o;

        if (tailNumber != null ? !tailNumber.equals(aircraft.tailNumber) : aircraft.tailNumber != null) return false;
        if (aircraftName != null ? !aircraftName.equals(aircraft.aircraftName) : aircraft.aircraftName != null)
            return false;
        if (aircraftType != aircraft.aircraftType) return false;
        return status == aircraft.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (tailNumber != null ? tailNumber.hashCode() : 0);
        result = 31 * result + (aircraftName != null ? aircraftName.hashCode() : 0);
        result = 31 * result + (aircraftType != null ? aircraftType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
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

