package com.ppinol.coworkingapp.core.domain.meetingRoom.reservation;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;

import java.util.Date;

public class MeetingRoomReservation {

    private final MeetingRoomReservationId meetingRoomReservationId;
    private final MeetingRoomId meetingRoomId;
    private final String userId;
    private final MeetingRoomReservationDate date;
    private final MeetingRoomReservationHour hour;
    private final MeetingRoomReservationDuration duration;
    private final MeetingRoomReservationStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public MeetingRoomReservation(MeetingRoomId meetingRoomId,
                                  String userId,
                                  MeetingRoomReservationDate date,
                                  MeetingRoomReservationHour hour,
                                  MeetingRoomReservationDuration duration) {
        this.meetingRoomReservationId = MeetingRoomReservationId.generate();
        this.meetingRoomId = meetingRoomId;
        this.userId = userId;
        this.date = date;
        this.hour = hour;
        this.duration = duration;
        this.status = MeetingRoomReservationStatus.create();
        this.createdAt = new Date();
        this.updatedAt = new Date();

        if (this.date.isToday() && this.hour.isBeforeOrNow()) {
            throw new InvalidMeetingRoomReservationDateException("Reservation date must be at least for the next hour");
        }
    }

    public MeetingRoomReservationId getReservationId() {
        return meetingRoomReservationId;
    }

    public MeetingRoomId getMeetingRoomId() {
        return meetingRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public MeetingRoomReservationDate getDate() {
        return date;
    }

    public MeetingRoomReservationHour getHour() {
        return hour;
    }

    public MeetingRoomReservationDuration getDuration() {
        return duration;
    }

    public MeetingRoomReservationStatus getStatus() {
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

    public boolean overlapsWith(MeetingRoomReservation other) {
        if (!this.date.isSameDay(other.date)) {
            return false;
        }

        int thisStart = this.hour.hour();
        int thisEnd = thisStart + this.duration.duration();

        int otherStart = other.hour.hour();
        int otherEnd = otherStart + other.duration.duration();

        return thisStart < otherEnd && thisEnd > otherStart;
    }

    @Override
    public String toString() {
        return "MeetingRoomReservation{" +
                "reservationId=" + meetingRoomReservationId +
                ", meetingRoomId=" + meetingRoomId +
                ", userId=" + userId +
                ", date=" + date +
                ", hour=" + hour +
                ", duration=" + duration +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
