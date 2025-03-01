package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.Status;

public class HotDeskStatus {

    private Status status;

    HotDeskStatus(Status status) {
        this.status = status;
    }

    public static HotDeskStatus create() {
        return new HotDeskStatus(Status.ACTIVE);
    }

    public Status value() {
        return status;
    }
}
