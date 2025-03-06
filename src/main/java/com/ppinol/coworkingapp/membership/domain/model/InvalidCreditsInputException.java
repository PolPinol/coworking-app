package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.exceptions.BadRequestException;

public class InvalidCreditsInputException extends BadRequestException {
    public InvalidCreditsInputException(String message) {
        super(message);
    }
}
