package com.ppinol.coworkingapp.core.ui;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String error) {
    public static ErrorResponseBuilder builder() {
        return new ErrorResponseBuilder();
    }
}
