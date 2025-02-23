package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.Id;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskId;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskRepository;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomRepository;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservation;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationDate;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationDuration;
import com.ppinol.coworkingapp.core.domain.meetingRoom.reservation.MeetingRoomReservationHour;
import org.springframework.stereotype.Service;


@Service
public class ReserveMeetingRoomCommandHandler {

    private final MeetingRoomRepository meetingRoomRepository;
    private final HotDeskRepository hotDeskRepository;

    public ReserveMeetingRoomCommandHandler(MeetingRoomRepository meetingRoomRepository,
                                            HotDeskRepository hotDeskRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.hotDeskRepository = hotDeskRepository;
    }

    public void handle(ReserveMeetingRoomCommand command) {
        MeetingRoomId meetingRoomId = new MeetingRoomId(command.meetingRoomId());

        MeetingRoom meetingRoom = this.meetingRoomRepository.findById(meetingRoomId);
        if (meetingRoom == null) {
            throw new MeetingRoomNotFoundException("MeetingRoom with id " + command.meetingRoomId() + " not found");
        }

        String userId = command.userId();
        MeetingRoomReservationDate date = new MeetingRoomReservationDate(command.date());
        MeetingRoomReservationHour hour = new MeetingRoomReservationHour(command.hour());
        MeetingRoomReservationDuration duration = new MeetingRoomReservationDuration(command.duration());

        MeetingRoomReservation reservation = new MeetingRoomReservation(
                meetingRoomId, userId, date, hour, duration
        );
        meetingRoom.reserve(reservation);
        this.meetingRoomRepository.save(meetingRoom);

        HotDeskReservationDate hotDeskReservationDate = new HotDeskReservationDate(command.date());
        HotDesk hotDesk = this.hotDeskRepository.findFirstAvailable(hotDeskReservationDate);

        if (hotDesk != null) {
            HotDeskReservation hotDeskReservation = new HotDeskReservation(
                    hotDesk.getId(), userId, hotDeskReservationDate
            );
            hotDesk.reserve(hotDeskReservation);
            this.hotDeskRepository.save(hotDesk);
        }
    }
}
