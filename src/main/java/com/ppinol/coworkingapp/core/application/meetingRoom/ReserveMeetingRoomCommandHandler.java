package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomRepository;
import com.ppinol.coworkingapp.core.domain.reservation.meetingRoom.*;
import org.springframework.stereotype.Service;


@Service
public class ReserveMeetingRoomCommandHandler {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRoomReservationRepository reservationRepository;

    public ReserveMeetingRoomCommandHandler(MeetingRoomRepository meetingRoomRepository,
                                            MeetingRoomReservationRepository reservationRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservationRepository = reservationRepository;
    }

    public void handle(ReserveMeetingRoomCommand command) {
        MeetingRoomId meetingRoomId = new MeetingRoomId(command.meetingRoomId());
        if (this.meetingRoomRepository.findById(meetingRoomId) == null) {
            throw new MeetingRoomNotFoundException("MeetingRoom with id " + command.meetingRoomId() + " not found");
        }

        UserId userId = new UserId(command.userId());
        MeetingRoomReservationDate date = new MeetingRoomReservationDate(command.date());

        if (this.reservationRepository.findAll(meetingRoomId, date)
                .stream()
                .anyMatch(r -> r.overlapsWith(command.date(), command.hour(), command.duration()))) {
            throw new OverlappingMeetingRoomReservationException("Reservation overlaps with an existing reservation");
        }

        MeetingRoomReservation newReservation = new MeetingRoomReservation(
                meetingRoomId.value(), userId, command.date(), command.hour(), command.duration()
        );
        this.reservationRepository.save(newReservation);
    }
}
