package com.ppinol.coworkingapp.core.ui;

public record ErrorResponse(int status, String error) {
    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }
}
