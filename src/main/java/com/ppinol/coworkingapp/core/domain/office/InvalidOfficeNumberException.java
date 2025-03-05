package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidOfficeNumberException extends BadRequestException {
    public InvalidOfficeNumberException(String message) {
        super(message);
    }
}
