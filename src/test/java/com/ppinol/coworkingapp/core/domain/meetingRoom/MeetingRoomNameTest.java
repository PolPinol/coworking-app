package com.ppinol.coworkingapp.core.domain.meetingRoom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomNameTest {

    @Test
    void testValidName() {
        MeetingRoomName name = new MeetingRoomName("Board Room");
        assertEquals("Board Room", name.value());
    }

    @Test
    void testNullNameThrowsException() {
        Exception exception = assertThrows(InvalidMeetingRoomNameException.class, () -> {
            new MeetingRoomName(null);
        });
        assertEquals("Meeting room name must not be empty", exception.getMessage());
    }

    @Test
    void testEmptyNameThrowsException() {
        Exception exception = assertThrows(InvalidMeetingRoomNameException.class, () -> {
            new MeetingRoomName("");
        });
        assertEquals("Meeting room name must not be empty", exception.getMessage());
    }
}
