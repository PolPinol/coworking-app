package com.ppinol.coworkingapp.core.application.hotdesk;

import com.ppinol.coworkingapp.core.exceptions.DuplicatedException;

public class DuplicatedHotDeskException extends DuplicatedException {
  public DuplicatedHotDeskException(String message) {
    super(message);
  }
}
