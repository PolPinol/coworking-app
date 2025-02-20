package com.ppinol.coworkingapp.core.domain.hotdesk;

public interface HotDeskRepository {
    void save(HotDesk hotDesk);
    HotDesk findByNumber(HotDeskNumber number);
    void clear();
}
