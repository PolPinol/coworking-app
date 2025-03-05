package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.exceptions.BadRequestException;

public class InvalidUserIdException extends BadRequestException {
    public InvalidUserIdException(String message) {
        super(message);
    }
}
