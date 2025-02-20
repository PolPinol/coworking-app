package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.Id;

public class HotDeskId extends Id {
    HotDeskId(String id) {
        super(id);
    }

    public static HotDeskId generate() {
        return new HotDeskId(generateId());
    }
}
