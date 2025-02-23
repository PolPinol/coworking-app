package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

public record MeetingRoomReservationDuration(int duration) {

    public MeetingRoomReservationDuration {
        if (duration < 1 || duration > 12) {
            throw new InvalidMeetingRoomReservationDurationException(
                    "Duration must be between 1 and 12 hours"
            );
        }
    }
}
