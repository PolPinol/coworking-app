package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;

import java.util.List;

public interface MeetingRoomReservationRepository {
    void save(MeetingRoomReservation meetingRoomReservation);
    List<MeetingRoomReservation> findAll(MeetingRoomId id, MeetingRoomReservationDate date);
}
