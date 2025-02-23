package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservation;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.OverlappingMeetingRoomReservationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MeetingRoom {
    private final MeetingRoomId id;
    private final MeetingRoomName name;
    private final MeetingRoomCapacity capacity;
    private MeetingRoomStatus status;
    private final Date createdAt;
    private Date updatedAt;

    private final List<MeetingRoomReservation> reservations;

    public MeetingRoom(MeetingRoomName name, MeetingRoomCapacity capacity) {
        this.id = MeetingRoomId.generate();
        this.name = name;
        this.capacity = capacity;
        this.status = MeetingRoomStatus.create();
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.reservations = new ArrayList<>();
    }

    public MeetingRoomId getId() {
        return id;
    }

    public MeetingRoomName getName() {
        return name;
    }

    public MeetingRoomCapacity getCapacity() {
        return capacity;
    }

    public MeetingRoomStatus getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void markAsUpdated() {
        this.updatedAt = new Date();
    }

    public void reserve(MeetingRoomReservation newReservation) {
        for (MeetingRoomReservation existingReservation : this.reservations) {
            if (existingReservation.overlapsWith(newReservation)) {
                throw new OverlappingMeetingRoomReservationException("Reservation overlaps with an existing reservation");
            }
        }

        this.reservations.add(newReservation);
    }
}
