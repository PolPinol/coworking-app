package com.ppinol.coworkingapp.core.application.meetingRoom;

public record ReserveMeetingRoomCommand(String meetingRoomId, String date, int hour, int duration, String userId) {}
