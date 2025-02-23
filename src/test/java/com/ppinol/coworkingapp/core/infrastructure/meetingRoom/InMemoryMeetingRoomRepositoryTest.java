package com.ppinol.coworkingapp.core.infrastructure.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomCapacity;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
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
    void testSaveAndFindMeetingRoomByName() {
        MeetingRoomName name = new MeetingRoomName("Room A");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(15);
        MeetingRoom room = new MeetingRoom(name, capacity);

        repository.save(room);
        MeetingRoom found = repository.findByName(new MeetingRoomName("Room A"));
        assertNotNull(found);
        assertEquals("Room A", found.getName().value());
    }

    @Test
    void testSaveAndFindMeetingRoomById() {
        MeetingRoomName name = new MeetingRoomName("Room B");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(20);
        MeetingRoom room = new MeetingRoom(name, capacity);
        MeetingRoomId id = room.getId();
        repository.save(room);
        MeetingRoom found = repository.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    void testSaveUpdatesExistingMeetingRoom() throws InterruptedException {
        MeetingRoomName name = new MeetingRoomName("Room C");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(25);
        MeetingRoom room = new MeetingRoom(name, capacity);
        repository.save(room);

        repository.save(room);
        MeetingRoom found = repository.findById(room.getId());
        assertNotNull(found);
    }

    @Test
    void testClearRepository() {
        MeetingRoomName name = new MeetingRoomName("Room D");
        MeetingRoomCapacity capacity = new MeetingRoomCapacity(30);
        MeetingRoom room = new MeetingRoom(name, capacity);
        repository.save(room);
        repository.clear();
        MeetingRoom found = repository.findByName(new MeetingRoomName("Room D"));
        assertNull(found, "Repository should be empty after clear");
    }
}
