package com.ppinol.coworkingapp.membership.domain.model;

public class UserId {

    private final String value;

    UserId(String value) {
        this.value = value;
    }

    public static UserId create(String value) {
        if (value == null) {
            throw new InvalidUserIdException("UserId should not be null");
        }

        if (value.trim().isEmpty()) {
            throw new InvalidUserIdException("UserId should not be empty");
        }

        return new UserId(value);
    }

    public static UserId empty() {
        return new UserId("");
    }

    public String value() {
        return value;
    }
}
