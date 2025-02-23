package com.ppinol.coworkingapp.core.domain;

public abstract class Status {
    public static final String ACTIVE = "Active";
    public static final String INACTIVE = "Inactive";

    protected final String status;

    protected Status(String status) {
        this.status = status;

        if (!status.equals(ACTIVE) && !status.equals(INACTIVE)) {
            throw new InvalidStatusException("Invalid status: " + status);
        }
    }

    public boolean isActive() {
        return ACTIVE.equals(status);
    }

    @Override
    public String toString() {
        return status;
    }
}
