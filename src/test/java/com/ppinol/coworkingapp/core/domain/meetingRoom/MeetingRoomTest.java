package com.ppinol.coworkingapp.core.domain.meetingRoom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomTest {

    @Test
    void testMeetingRoomCreation() {
        MeetingRoomName name = new MeetingRoomName("Team Room");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity("10");
        MeetingRoom room = new MeetingRoom(name, capacity);

        assertNotNull(room.getId());
        assertEquals("Team Room", room.getName().value());
        assertEquals(10, room.getCapacity().value());
        assertNotNull(room.getStatus());

        assertTrue(room.getStatus().isActive());
        assertNotNull(room.getCreatedAt());
        assertNotNull(room.getUpdatedAt());
    }
}
