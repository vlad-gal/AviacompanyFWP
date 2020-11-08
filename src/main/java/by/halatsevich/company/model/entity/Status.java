package by.halatsevich.company.model.entity;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    FLY("Fly"),
    BUSY("Busy");

    private String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
