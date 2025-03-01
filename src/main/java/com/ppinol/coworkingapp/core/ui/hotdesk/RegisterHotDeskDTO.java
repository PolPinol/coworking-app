package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterHotDeskDTO(Integer number) {

    @JsonCreator
    public RegisterHotDeskDTO(@JsonProperty("number") Integer number) {
        if (number == null) {
            throw new InvalidRegisterHotDeskInputException("Missing 'number' field in request body.");
        }

        this.number = number;
    }
}

