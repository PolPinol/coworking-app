package com.ppinol.coworkingapp.core.ui.meetingRoom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReserveMeetingRoomDTOTest {

    @Test
    void testValidInput() {
        String meetingRoomId = "room-123";
        String date = "2025-02-24";
        Integer hour = 10;
        Integer duration = 2;
        String userId = "user-123";

        ReserveMeetingRoomDTO dto = new ReserveMeetingRoomDTO(meetingRoomId, date, hour, duration, userId);
        assertEquals(meetingRoomId, dto.meetingRoomId());
        assertEquals(date, dto.date());
        assertEquals(hour, dto.hour());
        assertEquals(duration, dto.duration());
        assertEquals(userId, dto.userId());
    }

    @Test
    void testNullInputThrowsException() {
        Exception ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO(null, "2025-02-24", 10, 2, "user-123")
        );
        assertEquals("All fields should not be null", ex.getMessage());

        ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO("room-123", null, 10, 2, "user-123")
        );
        assertEquals("All fields should not be null", ex.getMessage());

        ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO("room-123", "2025-02-24", null, 2, "user-123")
        );
        assertEquals("All fields should not be null", ex.getMessage());

        ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO("room-123", "2025-02-24", 10, null, "user-123")
        );
        assertEquals("All fields should not be null", ex.getMessage());

        ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO("room-123", "2025-02-24", 10, 2, null)
        );
        assertEquals("All fields should not be null", ex.getMessage());
    }

    @Test
    void testEmptyInputThrowsException() {
        Exception ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO("   ", "2025-02-24", 10, 2, "user-123")
        );
        assertEquals("All fields should not be empty", ex.getMessage());

        ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO("room-123", "   ", 10, 2, "user-123")
        );
        assertEquals("All fields should not be empty", ex.getMessage());

        ex = assertThrows(InvalidReserveMeetingRoomInputException.class, () ->
                new ReserveMeetingRoomDTO("room-123", "2025-02-24", 10, 2, "   ")
        );
        assertEquals("All fields should not be empty", ex.getMessage());
    }
}
