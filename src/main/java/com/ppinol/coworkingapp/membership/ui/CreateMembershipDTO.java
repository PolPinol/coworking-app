package com.ppinol.coworkingapp.membership.ui;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateMembershipDTO(String userId) {

    @JsonCreator
    public CreateMembershipDTO(@JsonProperty("userId") String userId) {
        if (userId == null) {
            throw new InvalidCreateMembershipInputException("userId should not be null");
        }

        if (userId.trim().isEmpty()) {
            throw new InvalidCreateMembershipInputException("userId should not be empty");
        }

        this.userId = userId;
    }
}
