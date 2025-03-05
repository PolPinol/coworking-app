package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.exceptions.ConflictException;

public class OverlappingMeetingRoomReservationException extends ConflictException {
  public OverlappingMeetingRoomReservationException(String message) {
    super(message);
  }
}
