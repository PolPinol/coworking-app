package com.ppinol.coworkingapp.core.domain.meetingRoom;

public interface MeetingRoomRepository {
    void save(MeetingRoom meetingRoom);
    MeetingRoom findByName(MeetingRoomName name);
    MeetingRoom findById(MeetingRoomId id);
    void clear();
}
