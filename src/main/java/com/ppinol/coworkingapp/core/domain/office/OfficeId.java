package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.Id;

public class OfficeId extends Id {
    OfficeId(String id) {
        super(id);
    }

    public static OfficeId generate() {
        return new OfficeId(generateId());
    }
}