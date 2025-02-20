package com.ppinol.coworkingapp.core.domain.hotdesk;

import java.util.UUID;

public class HotDeskId {
    private final String id;

    HotDeskId(String id) {
        this.id = id;
    }

    public static HotDeskId generate() {
        return new HotDeskId(UUID.randomUUID().toString());
    }

    public String id() {
        return id;
    }
}
