package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservation;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationDate;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationDuration;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationHour;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.OverlappingMeetingRoomReservationException;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomTest {

    @Test
    void testMeetingRoomCreation() {
        MeetingRoomName name = new MeetingRoomName("Team Room");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(10);
        MeetingRoom room = new MeetingRoom(name, capacity);

        assertNotNull(room.getId());
        assertEquals("Team Room", room.getName().value());
        assertEquals(10, room.getCapacity().value());
        assertNotNull(room.getStatus());
        assertTrue(room.getStatus().isActive());
        assertNotNull(room.getCreatedAt());
        assertNotNull(room.getUpdatedAt());
    }

    @Test
    void testMeetingRoomReserveSuccess() {
        MeetingRoomName name = new MeetingRoomName("Team Room");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(10);
        MeetingRoom room = new MeetingRoom(name, capacity);

        // Create a reservation (for example, for "2025-02-24" at 10:00 for 2 hours).
        MeetingRoomReservation reservation = new MeetingRoomReservation(
                room.getId(),
                "user1",
                new MeetingRoomReservationDate("2025-02-24"),
                new MeetingRoomReservationHour(10),
                new MeetingRoomReservationDuration(2)
        );

        // Reserve the meeting room.
        room.reserve(reservation);
    }

    @Test
    void testMeetingRoomReserveOverlappingThrows() {
        MeetingRoomName name = new MeetingRoomName("Team Room");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(10);
        MeetingRoom room = new MeetingRoom(name, capacity);

        // Create an initial reservation for "2025-02-24" at 10:00 for 2 hours.
        MeetingRoomReservation reservation1 = new MeetingRoomReservation(
                room.getId(),
                "user1",
                new MeetingRoomReservationDate("2025-02-24"),
                new MeetingRoomReservationHour(10),
                new MeetingRoomReservationDuration(2)
        );
        room.reserve(reservation1);

        // Create another reservation that overlaps (e.g., starting at 11:00 for 2 hours).
        MeetingRoomReservation reservation2 = new MeetingRoomReservation(
                room.getId(),
                "user2",
                new MeetingRoomReservationDate("2025-02-24"),
                new MeetingRoomReservationHour(11),
                new MeetingRoomReservationDuration(2)
        );

        // Expect an exception because the new reservation overlaps with the existing one.
        assertThrows(OverlappingMeetingRoomReservationException.class, () -> room.reserve(reservation2));
    }

    @Test
    void testMarkAsUpdated() throws InterruptedException {
        MeetingRoomName name = new MeetingRoomName("Team Room");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(10);
        MeetingRoom room = new MeetingRoom(name, capacity);
        Date oldUpdatedAt = room.getUpdatedAt();
        // Introduce a small delay to ensure the timestamp will change.
        Thread.sleep(10);
        room.markAsUpdated();
        Date newUpdatedAt = room.getUpdatedAt();
        assertTrue(newUpdatedAt.after(oldUpdatedAt));
    }
}
