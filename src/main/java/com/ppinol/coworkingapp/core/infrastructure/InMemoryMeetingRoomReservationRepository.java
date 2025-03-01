package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;
import com.ppinol.coworkingapp.core.domain.reservation.meetingRoom.MeetingRoomReservation;
import com.ppinol.coworkingapp.core.domain.reservation.meetingRoom.MeetingRoomReservationDate;
import com.ppinol.coworkingapp.core.domain.reservation.meetingRoom.MeetingRoomReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryMeetingRoomReservationRepository implements MeetingRoomReservationRepository {
    private final List<MeetingRoomReservation> reservations = new ArrayList<>();

    @Override
    public void save(MeetingRoomReservation reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public List<MeetingRoomReservation> findAll(MeetingRoomId id, MeetingRoomReservationDate date) {
        return reservations.stream()
                .filter(res -> res.getMeetingRoomId().equals(id) && res.getDate().isSameDay(date))
                .collect(Collectors.toList());
    }
}
