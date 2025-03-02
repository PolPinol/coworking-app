package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskId;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservationRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryHotDeskReservationRepository implements HotDeskReservationRepository {
    private final List<HotDeskReservation> reservations = new ArrayList<>();

    @Override
    public void save(HotDeskReservation reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public HotDeskReservation find(UserId id, HotDeskReservationDate date) {
        return reservations.stream()
                .filter(res -> res.getUserId().equals(id) && res.getDate().isSameDay(date))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isHotDeskAvailable(HotDeskId id, HotDeskReservationDate date) {
        return reservations.stream()
                .noneMatch(res -> res.getHotDeskId().equals(id) && res.getDate().isSameDay(date));
    }

    @Override
    public void clear() {
        this.reservations.clear();
    }
}
