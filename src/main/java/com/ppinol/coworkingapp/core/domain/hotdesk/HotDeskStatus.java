package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.Status;

public class HotDeskStatus extends Status {
    HotDeskStatus(String status) {
        super(status);
    }

    public static HotDeskStatus create() {
        return new HotDeskStatus(ACTIVE);
    }
}
