package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidOfficeLeasePeriodException extends BadRequestException {
    public InvalidOfficeLeasePeriodException(String message) {
        super(message);
    }
}
