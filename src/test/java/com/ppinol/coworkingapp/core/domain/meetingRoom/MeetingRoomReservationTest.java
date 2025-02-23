package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.InvalidMeetingRoomReservationDateException;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservation;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationDate;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationDuration;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationHour;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomReservationTest {

    private MeetingRoomId dummyRoomId() {
        return MeetingRoomId.generate();
    }

    @Test
    void testValidReservationCreation() {
        MeetingRoomReservation reservation = new MeetingRoomReservation(
                dummyRoomId(),
                "user1",
                new MeetingRoomReservationDate("2050-01-01"),
                new MeetingRoomReservationHour(10),
                new MeetingRoomReservationDuration(2)
        );

        assertNotNull(reservation.getReservationId());
        assertEquals("user1", reservation.getUserId());
        assertNotNull(reservation.getDate());
        assertNotNull(reservation.getHour());
        assertNotNull(reservation.getDuration());
        assertNotNull(reservation.getStatus());
        assertNotNull(reservation.getCreatedAt());
        assertNotNull(reservation.getUpdatedAt());
    }

    @Test
    void testReservationThrowsForInvalidTodayReservation() {
        String today = LocalDate.now().toString();
        int currentHour = java.time.LocalTime.now().getHour();

        // This reservation should throw because the hour is "before or now"
        assertThrows(InvalidMeetingRoomReservationDateException.class, () -> {
            new MeetingRoomReservation(
                    dummyRoomId(),
                    "user2",
                    new MeetingRoomReservationDate(today),
                    new MeetingRoomReservationHour(currentHour),
                    new MeetingRoomReservationDuration(2)
            );
        });
    }

    @Test
    void testOverlapsWithReturnsTrue() {
        MeetingRoomReservation reservation1 = new MeetingRoomReservation(
                dummyRoomId(),
                "user1",
                new MeetingRoomReservationDate("2050-01-01"),
                new MeetingRoomReservationHour(10),
                new MeetingRoomReservationDuration(2)
        );

        MeetingRoomReservation reservation2 = new MeetingRoomReservation(
                dummyRoomId(),
                "user2",
                new MeetingRoomReservationDate("2050-01-01"),
                new MeetingRoomReservationHour(11), // Overlap: 10-12 vs 11-13
                new MeetingRoomReservationDuration(2)
        );

        assertTrue(reservation1.overlapsWith(reservation2));
    }

    @Test
    void testOverlapsWithReturnsFalse() {
        MeetingRoomReservation reservation1 = new MeetingRoomReservation(
                dummyRoomId(),
                "user1",
                new MeetingRoomReservationDate("2050-01-01"),
                new MeetingRoomReservationHour(10),
                new MeetingRoomReservationDuration(2)
        );

        MeetingRoomReservation reservation2 = new MeetingRoomReservation(
                dummyRoomId(),
                "user2",
                new MeetingRoomReservationDate("2050-01-01"),
                new MeetingRoomReservationHour(13), // No overlap: 10-12 vs 13-15
                new MeetingRoomReservationDuration(2)
        );

        assertFalse(reservation1.overlapsWith(reservation2));
    }
}
