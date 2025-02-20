package com.ppinol.coworkingapp.core.ui;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ErrorResponseBuilder {
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message = "An unexpected error occurred";

    ErrorResponseBuilder() {}

    public ErrorResponseBuilder status(HttpStatus status) {
        this.status = status;
        return this;
    }

    public ErrorResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public ResponseEntity<ErrorResponse> build() {
        ErrorResponse errorResponse = new ErrorResponse(
                this.status,
                this.message
        );
        return ResponseEntity.status(this.status).body(errorResponse);
    }
}

