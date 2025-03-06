package com.ppinol.coworkingapp.membership.domain.model;

public record Credits(int value) {
    public Credits {
        if (value <= 0) {
            throw new InvalidCreditsInputException("credits value must be greater than 0");
        }
    }
}
