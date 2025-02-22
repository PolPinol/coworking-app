package com.ppinol.coworkingapp.core.domain.office;

public class OfficeStatus {
    private static final String ACTIVE = "Active";
    private static final String INACTIVE = "Inactive";

    private final String status;

    public OfficeStatus(String status) {
        if (status == null || status.isEmpty()) {
            this.status = ACTIVE;
        } else if (status.equals(ACTIVE) || status.equals(INACTIVE)) {
            this.status = status;
        } else {
            throw new InvalidOfficeStatusException("Invalid office status: " + status);
        }
    }

    public String getStatus() {
        return status;
    }
}
