package com.ppinol.coworkingapp.core.domain.office;

public class InvalidOfficeLeasePeriodException extends RuntimeException {
    public InvalidOfficeLeasePeriodException(String message) {
        super(message);
    }
}
