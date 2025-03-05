package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.exceptions.BadRequestException;

public class InvalidCreateMembershipInputException extends BadRequestException {
    public InvalidCreateMembershipInputException(String message) {
        super(message);
    }
}
