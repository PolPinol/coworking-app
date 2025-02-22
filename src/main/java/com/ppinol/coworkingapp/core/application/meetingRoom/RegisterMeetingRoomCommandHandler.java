package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomCapacity;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterMeetingRoomCommandHandler {

    private final MeetingRoomRepository repository;

    public RegisterMeetingRoomCommandHandler(MeetingRoomRepository repository) {
        this.repository = repository;
    }

    public void handle(RegisterMeetingRoomCommand command) {
        MeetingRoomName name = new MeetingRoomName(command.name());

        if (this.repository.findByName(name) != null) {
            throw new DuplicatedMeetingRoomException("MeetingRoom name already exists");
        }

        MeetingRoomCapacity capacity = new MeetingRoomCapacity(command.capacity());
        MeetingRoom meetingRoom = new MeetingRoom(name, capacity);
        this.repository.save(meetingRoom);
    }
}
