package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

public record MeetingRoomReservationHour(int value) {

    public MeetingRoomReservationHour {
        if (value < 0 || value > 23) {
            throw new InvalidMeetingRoomReservationHourException(
                    "Hour must be between 0 and 23"
            );
        }
    }

    public boolean isBeforeOrNow() {
        return value <= java.time.LocalTime.now().getHour();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetingRoomReservationHour(int hour1))) return false;
        return value == hour1;
    }
}
