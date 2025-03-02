package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

public record MeetingRoomReservationDuration(int value) {

    public MeetingRoomReservationDuration {
        if (value < 1 || value > 12) {
            throw new InvalidMeetingRoomReservationDurationException(
                    "Duration must be between 1 and 12 hours"
            );
        }
    }
}
