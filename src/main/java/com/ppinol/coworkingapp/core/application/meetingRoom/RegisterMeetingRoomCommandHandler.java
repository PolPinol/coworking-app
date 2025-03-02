package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.EventPublisher;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomCapacity;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterMeetingRoomCommandHandler {

    private final MeetingRoomRepository repository;
    private final EventPublisher eventPublisher;

    public RegisterMeetingRoomCommandHandler(MeetingRoomRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(RegisterMeetingRoomCommand command) {
        MeetingRoomName name = new MeetingRoomName(command.name());

        if (this.repository.findByName(name) != null) {
            throw new DuplicatedMeetingRoomException("MeetingRoom name already exists");
        }

        MeetingRoom meetingRoom = new MeetingRoom(name.value(), command.capacity());
        this.repository.save(meetingRoom);

        this.eventPublisher.publish(meetingRoom.releaseEvents());
    }
}
