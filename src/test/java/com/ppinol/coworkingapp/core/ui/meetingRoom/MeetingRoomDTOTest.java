package com.ppinol.coworkingapp.core.ui.meetingRoom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomDTOTest {

    @Test
    void testValidDTO() {
        MeetingRoomDTO dto = new MeetingRoomDTO("Conference Room", "15");
        assertEquals("Conference Room", dto.name());
        assertEquals("15", dto.capacity());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(InvalidRegisterMeetingRoomInput.class, () -> {
            new MeetingRoomDTO(null, "10");
        });
        assertEquals("name and capacity should not be null", exception.getMessage());
    }

    @Test
    void testNullCapacityThrowsException() {
        Exception exception = assertThrows(InvalidRegisterMeetingRoomInput.class, () -> {
            new MeetingRoomDTO("Room", null);
        });
        assertEquals("name and capacity should not be null", exception.getMessage());
    }

    @Test
    void testEmptyNameThrowsException() {
        Exception exception = assertThrows(InvalidRegisterMeetingRoomInput.class, () -> {
            new MeetingRoomDTO("  ", "10");
        });
        assertEquals("name and capacity should not be empty", exception.getMessage());
    }

    @Test
    void testEmptyCapacityThrowsException() {
        Exception exception = assertThrows(InvalidRegisterMeetingRoomInput.class, () -> {
            new MeetingRoomDTO("Room", "   ");
        });
        assertEquals("name and capacity should not be empty", exception.getMessage());
    }
}
