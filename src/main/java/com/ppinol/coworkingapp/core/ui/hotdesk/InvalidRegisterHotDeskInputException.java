package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.ppinol.coworkingapp.core.exceptions.BadRequestException;

public class InvalidRegisterHotDeskInputException extends BadRequestException {
    public InvalidRegisterHotDeskInputException(String message) {
        super(message);
    }
}
