package com.ppinol.coworkingapp.core.application.hotdesk;

public class DuplicatedHotDeskException extends RuntimeException {
  public DuplicatedHotDeskException(String message) {
    super(message);
  }
}
