package com.ppinol.coworkingapp.core.ui.meetingRoom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RegisterMeetingRoomDTOTest {

    @Test
    void testValidDTO() {
        RegisterMeetingRoomDTO dto = new RegisterMeetingRoomDTO("Conference Room", 15);
        assertEquals("Conference Room", dto.name());
        assertEquals(15, dto.capacity());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(InvalidRegisterMeetingRoomInput.class, () -> {
            new RegisterMeetingRoomDTO(null, 10);
        });
        assertEquals("name and capacity should not be null", exception.getMessage());
    }

    @Test
    void testNullCapacityThrowsException() {
        Exception exception = assertThrows(InvalidRegisterMeetingRoomInput.class, () -> {
            new RegisterMeetingRoomDTO("Room", null);
        });
        assertEquals("name and capacity should not be null", exception.getMessage());
    }

    @Test
    void testEmptyNameThrowsException() {
        Exception exception = assertThrows(InvalidRegisterMeetingRoomInput.class, () -> {
            new RegisterMeetingRoomDTO("  ", 10);
        });
        assertEquals("name should not be empty", exception.getMessage());
    }
}
