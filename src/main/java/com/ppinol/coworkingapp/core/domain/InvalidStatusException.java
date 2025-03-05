package com.ppinol.coworkingapp.core.domain;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidStatusException extends BadRequestException {
    public InvalidStatusException(String message) {
        super(message);
    }
}
