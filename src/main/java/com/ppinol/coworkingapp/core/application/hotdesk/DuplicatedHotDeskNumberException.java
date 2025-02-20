package com.ppinol.coworkingapp.core.application.hotdesk;

public class DuplicatedHotDeskNumberException extends RuntimeException {
  public DuplicatedHotDeskNumberException(String message) {
    super(message);
  }
}
