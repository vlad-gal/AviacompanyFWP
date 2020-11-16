package by.halatsevich.company.entity;

/**
 * The enum represents status.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public enum Status {
    /**
     * Active status.
     */
    ACTIVE("Active"),
    /**
     * Inactive status.
     */
    INACTIVE("Inactive"),
    /**
     * Fly status.
     */
    FLY("Fly"),
    /**
     * Busy status.
     */
    BUSY("Busy");

    private String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    /**
     * Gets status name.
     *
     * @return the status name
     */
    public String getStatusName() {
        return statusName;
    }
}
