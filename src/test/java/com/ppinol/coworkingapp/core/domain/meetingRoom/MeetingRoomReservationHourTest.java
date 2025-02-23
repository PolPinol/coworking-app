package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.InvalidMeetingRoomReservationHourException;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationHour;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomReservationHourTest {

    @Test
    void testValidHourCreation() {
        MeetingRoomReservationHour hour = new MeetingRoomReservationHour(10);
        assertEquals(10, hour.hour());
    }

    @Test
    void testInvalidHourTooLow() {
        assertThrows(InvalidMeetingRoomReservationHourException.class, () -> new MeetingRoomReservationHour(-1));
    }

    @Test
    void testInvalidHourTooHigh() {
        assertThrows(InvalidMeetingRoomReservationHourException.class, () -> new MeetingRoomReservationHour(24));
    }

    @Test
    void testIsBeforeOrNow() {
        int current = LocalTime.now().getHour();

        if (current > 0) {
            MeetingRoomReservationHour earlier = new MeetingRoomReservationHour(current - 1);
            assertTrue(earlier.isBeforeOrNow());
        }

        if (current < 23) {
            MeetingRoomReservationHour later = new MeetingRoomReservationHour(current + 1);
            assertFalse(later.isBeforeOrNow());
        }
    }

    @Test
    void testEquals() {
        MeetingRoomReservationHour hour1 = new MeetingRoomReservationHour(10);
        MeetingRoomReservationHour hour2 = new MeetingRoomReservationHour(10);
        MeetingRoomReservationHour hour3 = new MeetingRoomReservationHour(11);

        assertEquals(hour1, hour2);
        assertNotEquals(hour1, hour3);
    }
}
