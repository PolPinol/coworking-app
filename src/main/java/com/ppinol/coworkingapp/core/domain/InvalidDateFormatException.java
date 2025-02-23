package com.ppinol.coworkingapp.core.domain;

import com.ppinol.coworkingapp.core.BadRequestException;

import java.time.format.DateTimeParseException;

public class InvalidDateFormatException extends BadRequestException {
  public InvalidDateFormatException(String message, DateTimeParseException e) {
    super(message);
  }
}
