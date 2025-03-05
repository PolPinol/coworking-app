package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidReserveHotDeskInputException extends BadRequestException {
    public InvalidReserveHotDeskInputException(String message) {
        super(message);
    }
}
