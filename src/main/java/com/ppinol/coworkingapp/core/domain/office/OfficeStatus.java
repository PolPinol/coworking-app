package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.Status;

import java.util.Optional;

public class OfficeStatus {

    private Status status;

    public OfficeStatus(Optional<String> status) {
        this.status = status.map(Status::from).orElse(Status.ACTIVE);
    }

    public Status value() {
        return status;
    }
}
