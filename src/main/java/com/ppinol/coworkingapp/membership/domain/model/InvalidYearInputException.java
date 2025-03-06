package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.exceptions.BadRequestException;

public class InvalidYearInputException extends BadRequestException {
    public InvalidYearInputException(String message) {
        super(message);
    }
}
