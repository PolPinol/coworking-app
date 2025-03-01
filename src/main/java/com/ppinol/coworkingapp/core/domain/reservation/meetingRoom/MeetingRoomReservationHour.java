package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

public record MeetingRoomReservationHour(int hour) {

    public MeetingRoomReservationHour {
        if (hour < 0 || hour > 23) {
            throw new InvalidMeetingRoomReservationHourException(
                    "Hour must be between 0 and 23"
            );
        }
    }

    public boolean isBeforeOrNow() {
        return hour <= java.time.LocalTime.now().getHour();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetingRoomReservationHour(int hour1))) return false;
        return hour == hour1;
    }
}
