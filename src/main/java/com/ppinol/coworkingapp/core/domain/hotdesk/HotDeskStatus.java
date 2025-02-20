package com.ppinol.coworkingapp.core.domain.hotdesk;

public class HotDeskStatus {
    private static final String AVAILABLE = "Available";
    private static final String ACTIVE = "Active";

    private final String status;

    HotDeskStatus(String status) {
        this.status = status;
    }

    public static HotDeskStatus create() {
        return new HotDeskStatus(ACTIVE);
    }

    public static HotDeskStatus release() {
        return new HotDeskStatus(AVAILABLE);
    }

    public boolean isActive() {
        return status.equals(ACTIVE);
    }

    public boolean isAvailable() {
        return status.equals(AVAILABLE);
    }
}

