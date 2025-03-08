package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.exceptions.BadRequestException;

public class InvalidGetFullMembershipInputException extends BadRequestException {
    public InvalidGetFullMembershipInputException(String message) {
        super(message);
    }
}
