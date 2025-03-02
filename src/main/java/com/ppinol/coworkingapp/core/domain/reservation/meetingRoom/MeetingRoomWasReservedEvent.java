package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Event;
import com.ppinol.coworkingapp.core.domain.Id;

public class MeetingRoomWasReservedEvent extends Event<MeetingRoomWasReservedEventPayload> {
    private MeetingRoomWasReservedEvent(Id aggregateId, MeetingRoomWasReservedEventPayload payload) {
        super(aggregateId, "MeetingRoomWasReservedEvent", payload);
    }

    public static MeetingRoomWasReservedEvent from(MeetingRoomReservation reservation) {
        MeetingRoomWasReservedEventPayload payload = new MeetingRoomWasReservedEventPayload(
                reservation.getMeetingRoomId().value(),
                reservation.getUserId().value(),
                reservation.getDate().value(),
                reservation.getHour().value(),
                reservation.getDuration().value()
        );
        return new MeetingRoomWasReservedEvent(reservation.getMeetingRoomReservationId(), payload);
    }
}
