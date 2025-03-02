package com.ppinol.coworkingapp.core.domain.reservation.meetingRoom;

import com.ppinol.coworkingapp.core.domain.AggregateRoot;
import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;

import java.util.Date;

public class MeetingRoomReservation extends AggregateRoot {

    private final MeetingRoomReservationId meetingRoomReservationId;
    private final MeetingRoomId meetingRoomId;
    private final UserId userId;
    private final MeetingRoomReservationDate date;
    private final MeetingRoomReservationHour hour;
    private final MeetingRoomReservationDuration duration;
    private final MeetingRoomReservationStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public MeetingRoomReservation(String meetingRoomId,
                                  UserId userId,
                                  String date,
                                  int hour,
                                  int duration) {
        this.meetingRoomReservationId = MeetingRoomReservationId.generate();
        this.meetingRoomId = new MeetingRoomId(meetingRoomId);
        this.userId = userId;
        this.date = new MeetingRoomReservationDate(date);
        this.hour = new MeetingRoomReservationHour(hour);
        this.duration = new MeetingRoomReservationDuration(duration);
        this.status = MeetingRoomReservationStatus.create();
        this.createdAt = new Date();
        this.updatedAt = new Date();

        if (this.date.isToday() && this.hour.isBeforeOrNow()) {
            throw new InvalidMeetingRoomReservationDateException("Reservation date must be at least for the next hour");
        }

        this.recordEvent(MeetingRoomWasReservedEvent.from(this));
    }

    public MeetingRoomId getMeetingRoomId() {
        return meetingRoomId;
    }

    public MeetingRoomReservationId getMeetingRoomReservationId() {
        return meetingRoomReservationId;
    }

    public UserId getUserId() {
        return userId;
    }

    public MeetingRoomReservationDuration getDuration() {
        return duration;
    }

    public MeetingRoomReservationDate getDate() {
        return date;
    }

    public MeetingRoomReservationHour getHour() {
        return hour;
    }

    public MeetingRoomReservationStatus getStatus() {
        return status;
    }

    public void markAsUpdated() {
        this.updatedAt = new Date();
    }

    public boolean overlapsWith(String date, int hour, int duration) {
        MeetingRoomReservationDate otherDate = new MeetingRoomReservationDate(date);

        if (!this.date.isSameDay(otherDate)) {
            return false;
        }

        int thisStart = this.hour.value();
        int thisEnd = thisStart + this.duration.value();

        int otherEnd = hour + duration;

        return thisStart < otherEnd && thisEnd > hour;
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
