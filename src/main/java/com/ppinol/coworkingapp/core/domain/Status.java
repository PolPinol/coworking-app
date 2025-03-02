package com.ppinol.coworkingapp.core.domain;

public enum Status {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public static Status from(String status) {
        for (Status s : Status.values()) {
            if (s.displayName.equals(status)) {
                return s;
            }
        }
        throw new InvalidStatusException("Invalid status: " + status);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
