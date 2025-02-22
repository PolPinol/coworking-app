package com.ppinol.coworkingapp.core.domain.office;

public class InvalidOfficeStatusException extends RuntimeException {
    public InvalidOfficeStatusException(String message) {
        super(message);
    }
}
