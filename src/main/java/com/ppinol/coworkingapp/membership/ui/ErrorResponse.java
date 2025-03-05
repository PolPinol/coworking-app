package com.ppinol.coworkingapp.membership.ui;

public record ErrorResponse(int status, String error) {
    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }
}
