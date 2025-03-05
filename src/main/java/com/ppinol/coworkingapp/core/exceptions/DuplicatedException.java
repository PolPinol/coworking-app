package com.ppinol.coworkingapp.core.exceptions;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException(String message) {
        super(message);
    }
}
