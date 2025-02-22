package com.ppinol.coworkingapp.core.domain.office;

public class InvalidOfficeNumberException extends RuntimeException {
    public InvalidOfficeNumberException(String message) {
        super(message);
    }
}
