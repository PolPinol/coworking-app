package com.ppinol.coworkingapp.core.domain.meetingRoom;

import com.ppinol.coworkingapp.core.domain.AggregateRoot;

import java.util.Date;

public class MeetingRoom extends AggregateRoot {
    private final MeetingRoomId id;
    private final MeetingRoomName name;
    private final MeetingRoomCapacity capacity;
    private MeetingRoomStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public MeetingRoom(String name, int capacity) {
        this.id = MeetingRoomId.generate();
        this.name = new MeetingRoomName(name);
        this.capacity = new MeetingRoomCapacity(capacity);
        this.status = MeetingRoomStatus.create();
        this.createdAt = new Date();
        this.updatedAt = new Date();

        this.recordEvent(MeetingRoomWasRegisteredEvent.from(this));
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
}
