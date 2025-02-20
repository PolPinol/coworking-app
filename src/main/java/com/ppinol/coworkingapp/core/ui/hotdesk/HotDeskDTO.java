package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotDeskDTO {

    private final String number;

    @JsonCreator
    public HotDeskDTO(@JsonProperty("number") String number) {
        if (number == null) {
            throw new InvalidInputNumberException("Missing 'number' field in request body.");
        }

        number = number.trim();
        if (number.isEmpty()) {
            throw new InvalidInputNumberException("The 'number' field cannot be empty.");
        }

        this.number = number;
    }

    public String number() {
        return number;
    }
}

