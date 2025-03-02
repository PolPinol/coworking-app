package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMeetingRoomRepository implements MeetingRoomRepository {
    private final List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(MeetingRoom meetingRoom) {
        meetingRooms.add(meetingRoom);
    }

    @Override
    public MeetingRoom findByName(MeetingRoomName name) {
        return meetingRooms.stream()
                .filter(room -> room.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public MeetingRoom findById(MeetingRoomId id) {
        return meetingRooms.stream()
                .filter(room -> room.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void clear() {
        meetingRooms.clear();
    }
}
