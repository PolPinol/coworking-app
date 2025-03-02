package com.ppinol.coworkingapp.core.domain.reservation.hotdesk;

import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskId;

public interface HotDeskReservationRepository {
    void save(HotDeskReservation reservation);
    HotDeskReservation find(UserId id, HotDeskReservationDate date);
    boolean isHotDeskAvailable(HotDeskId id, HotDeskReservationDate date);
    void clear();
}
