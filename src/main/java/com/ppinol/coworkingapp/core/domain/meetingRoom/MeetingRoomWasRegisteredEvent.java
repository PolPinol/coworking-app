package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Event;
import com.ppinol.coworkingapp.core.domain.Id;

public class MeetingRoomWasRegisteredEvent extends Event<MeetingRoomWasRegisteredEventPayload> {

    private MeetingRoomWasRegisteredEvent(Id aggregateId, MeetingRoomWasRegisteredEventPayload payload) {
        super(aggregateId, "MeetingRoomWasRegisteredEvent", payload);
    }

    public static MeetingRoomWasRegisteredEvent from(MeetingRoom meetingRoom) {
        MeetingRoomWasRegisteredEventPayload payload = new MeetingRoomWasRegisteredEventPayload(
                meetingRoom.getName().value(),
                meetingRoom.getCapacity().value()
        );
        return new MeetingRoomWasRegisteredEvent(meetingRoom.getId(), payload);
    }
}
