package com.ppinol.coworkingapp.core.domain;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidStatusException extends BadRequestException {
    public InvalidStatusException(String message) {
        super(message);
    }
}
