package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.EventPublisher;
import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomRepository;
import com.ppinol.coworkingapp.core.domain.reservation.meetingRoom.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class ReserveMeetingRoomCommandHandler {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRoomReservationRepository reservationRepository;
    private final EventPublisher eventPublisher;

    public ReserveMeetingRoomCommandHandler(MeetingRoomRepository meetingRoomRepository,
                                            MeetingRoomReservationRepository reservationRepository,
                                            EventPublisher eventPublisher) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservationRepository = reservationRepository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(ReserveMeetingRoomCommand command) {
        MeetingRoomId meetingRoomId = new MeetingRoomId(command.meetingRoomId());
        if (this.meetingRoomRepository.findById(meetingRoomId) == null) {
            throw new MeetingRoomNotFoundException("MeetingRoom with id " + command.meetingRoomId() + " not found");
        }

        UserId userId = new UserId(command.userId());
        MeetingRoomReservationDate date = new MeetingRoomReservationDate(command.date());

        List<MeetingRoomReservation> reservations = Optional.ofNullable(
                        this.reservationRepository.findAll(meetingRoomId, date))
                .orElse(Collections.emptyList());
        if (reservations.stream().anyMatch(r -> r.overlapsWith(command.date(), command.hour(), command.duration()))) {
            throw new OverlappingMeetingRoomReservationException("Reservation overlaps with an existing reservation");
        }

        MeetingRoomReservation newReservation = new MeetingRoomReservation(
                meetingRoomId.value(), userId, command.date(), command.hour(), command.duration()
        );
        this.reservationRepository.save(newReservation);

        this.eventPublisher.publish(newReservation.releaseEvents());
    }
}
