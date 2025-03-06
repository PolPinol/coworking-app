package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryHotDeskRepository implements HotDeskRepository {
    private final List<HotDesk> hotDesks = new ArrayList<>();

    @Override
    public void save(HotDesk hotDesk) {
        hotDesks.add(hotDesk);
    }

    @Override
    public List<HotDesk> findAll() {
        return hotDesks;
    }

    @Override
    public HotDesk findByNumber(HotDeskNumber number) {
        return hotDesks.stream()
                .filter(hotDesk -> hotDesk.getNumber().equals(number))
                .findFirst()
                .orElse(null);
    }

    public void clear() {
        hotDesks.clear();
    }
}
