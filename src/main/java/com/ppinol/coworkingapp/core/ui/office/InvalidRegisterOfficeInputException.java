package com.ppinol.coworkingapp.core.ui.office;

import com.ppinol.coworkingapp.core.BadRequestException;

public class InvalidRegisterOfficeInputException extends BadRequestException {
    public InvalidRegisterOfficeInputException(String message) {
        super(message);
    }
}
