package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.Status;

import java.util.Optional;

public class OfficeStatus extends Status {

    public OfficeStatus(Optional<String> status) {
        super(status.orElse(ACTIVE));
    }
}
