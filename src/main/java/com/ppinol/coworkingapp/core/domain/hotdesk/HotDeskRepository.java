package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;

public interface HotDeskRepository {
    void save(HotDesk hotDesk);
    HotDesk findByNumber(HotDeskNumber number);
    HotDesk findById(HotDeskId id);
    HotDesk findFirstAvailable(HotDeskReservationDate date);
    void clear();
}
