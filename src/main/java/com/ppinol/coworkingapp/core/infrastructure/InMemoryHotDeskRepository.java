package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskId;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskRepository;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryHotDeskRepository implements HotDeskRepository {
    private final List<HotDesk> hotDesks = new ArrayList<>();

    @Override
    public void save(HotDesk hotDesk) {
        HotDesk existingHotDesk = findById(hotDesk.getId());
        if (existingHotDesk != null) {
            hotDesks.remove(hotDesk);
            hotDesk.markAsUpdated();
        }
        hotDesks.add(hotDesk);
    }

    @Override
    public HotDesk findByNumber(HotDeskNumber number) {
        return hotDesks.stream()
                .filter(hotDesk -> hotDesk.getNumber().equals(number))
                .findFirst()
                .orElse(null);
    }

    @Override
    public HotDesk findById(HotDeskId id) {
        return hotDesks.stream()
                .filter(hotDesk -> hotDesk.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public HotDesk findFirstAvailable(HotDeskReservationDate date) {
        return hotDesks.stream()
                .filter(hotDesk -> hotDesk.isAvailable(date))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void clear() {
        hotDesks.clear();
    }
}
