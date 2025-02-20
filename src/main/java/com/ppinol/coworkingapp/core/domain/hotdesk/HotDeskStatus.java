package com.ppinol.coworkingapp.core.domain.hotdesk;

public class HotDeskStatus {
    private static final String ACTIVE = "Active";

    private final String status;

    HotDeskStatus(String status) {
        this.status = status;
    }

    public static HotDeskStatus create() {
        return new HotDeskStatus(ACTIVE);
    }

    public boolean isActive() {
        return status.equals(ACTIVE);
    }
}

