package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.exceptions.BadRequestException;

public class InvalidMonthInputException extends BadRequestException {
    public InvalidMonthInputException(String message) {
        super(message);
    }
}
