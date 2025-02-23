package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ReserveMeetingRoomDTO(String meetingRoomId, String date, Integer hour, Integer duration, String userId) {

    @JsonCreator
    public ReserveMeetingRoomDTO(@JsonProperty("meetingRoomId") String meetingRoomId,
                                 @JsonProperty("date") String date,
                                 @JsonProperty("hour") Integer hour,
                                 @JsonProperty("duration") Integer duration,
                                 @JsonProperty("userId") String userId) {
        if (meetingRoomId == null || date == null || hour == null || duration == null || userId == null) {
            throw new InvalidReserveMeetingRoomInputException("All fields should not be null");
        }

        if (meetingRoomId.trim().isEmpty() || date.trim().isEmpty() || userId.trim().isEmpty()) {
            throw new InvalidReserveMeetingRoomInputException("All fields should not be empty");
        }

        this.meetingRoomId = meetingRoomId;
        this.date = date;
        this.hour = hour;
        this.duration = duration;
        this.userId = userId;
    }
}
