package com.ppinol.coworkingapp.core.infrastructure.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomCapacity;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryMeetingRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryMeetingRoomRepositoryTest {

    private InMemoryMeetingRoomRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryMeetingRoomRepository();
        repository.clear();
    }

    @Test
    void testSaveAndFindMeetingRoom() {
        MeetingRoomName name = new MeetingRoomName("Room A");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity("15");
        MeetingRoom room = new MeetingRoom(name, capacity);

        repository.save(room);
        MeetingRoom found = repository.findByName(new MeetingRoomName("Room A"));
        assertNotNull(found);
        assertEquals("Room A", found.getName().value());
    }
}
