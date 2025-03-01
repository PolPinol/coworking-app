package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ReserveHotDeskDTO(String userId, String date) {

    @JsonCreator
    public ReserveHotDeskDTO(@JsonProperty("userId") String userId, @JsonProperty("date") String date) {
        if (userId == null || date == null) {
            throw new InvalidReserveHotDeskInputException("All fields should not be null");
        }

        if (userId.trim().isEmpty() || date.trim().isEmpty()) {
            throw new InvalidReserveHotDeskInputException("All fields should not be empty");
        }

        this.userId = userId;
        this.date = date;
    }
}
