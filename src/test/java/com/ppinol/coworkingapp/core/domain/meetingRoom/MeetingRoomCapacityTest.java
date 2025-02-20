package com.ppinol.coworkingapp.core.domain.meetingRoom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomCapacityTest {

    @Test
    void testValidCapacity() {
        MeetingRoomCapacity capacity = new MeetingRoomCapacity("20");
        assertEquals(20, capacity.value());
    }

    @Test
    void testNonNumericCapacityThrowsException() {
        Exception exception = assertThrows(InvalidMeetingRoomCapacityException.class, () -> {
            new MeetingRoomCapacity("abc");
        });
        assertEquals("Meeting room must be a number", exception.getMessage());
    }

    @Test
    void testZeroCapacityThrowsException() {
        Exception exception = assertThrows(InvalidMeetingRoomCapacityException.class, () -> {
            new MeetingRoomCapacity("0");
        });
        assertEquals("Meeting room must be positive", exception.getMessage());
    }

    @Test
    void testNegativeCapacityThrowsException() {
        Exception exception = assertThrows(InvalidMeetingRoomCapacityException.class, () -> {
            new MeetingRoomCapacity("-5");
        });
        assertEquals("Meeting room must be positive", exception.getMessage());
    }
}
