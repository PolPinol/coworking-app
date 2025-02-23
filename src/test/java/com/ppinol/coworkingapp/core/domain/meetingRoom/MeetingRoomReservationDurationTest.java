package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.InvalidMeetingRoomReservationDurationException;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationDuration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomReservationDurationTest {

    @Test
    void testValidDuration() {
        MeetingRoomReservationDuration duration = new MeetingRoomReservationDuration(3);
        assertEquals(3, duration.duration());
    }

    @Test
    void testInvalidDurationTooLow() {
        assertThrows(InvalidMeetingRoomReservationDurationException.class, () -> new MeetingRoomReservationDuration(0));
    }

    @Test
    void testInvalidDurationTooHigh() {
        assertThrows(InvalidMeetingRoomReservationDurationException.class, () -> new MeetingRoomReservationDuration(13));
    }
}
