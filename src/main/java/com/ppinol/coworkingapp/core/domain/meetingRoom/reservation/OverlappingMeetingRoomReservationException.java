package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

import com.ppinol.coworkingapp.core.ConflictException;

public class OverlappingMeetingRoomReservationException extends ConflictException {
  public OverlappingMeetingRoomReservationException(String message) {
    super(message);
  }
}
