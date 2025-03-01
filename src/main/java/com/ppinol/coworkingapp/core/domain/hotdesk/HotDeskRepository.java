package com.ppinol.coworkingapp.core.domain.hotdesk;

import java.util.List;

public interface HotDeskRepository {
    void save(HotDesk hotDesk);
    List<HotDesk> findAll();
    HotDesk findByNumber(HotDeskNumber number);
    void clear();
}
