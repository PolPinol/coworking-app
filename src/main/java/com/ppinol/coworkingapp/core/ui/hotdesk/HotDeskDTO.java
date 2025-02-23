package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record HotDeskDTO(Integer number) {

    @JsonCreator
    public HotDeskDTO(@JsonProperty("number") Integer number) {
        if (number == null) {
            throw new InvalidRegisterHotDeskInputException("Missing 'number' field in request body.");
        }

        this.number = number;
    }
}

