package com.ppinol.coworkingapp.core.infrastructure.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskId;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryHotDeskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHotDeskRepositoryTest {

    private InMemoryHotDeskRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryHotDeskRepository();
        repository.clear();
    }

    @Test
    void testSaveAndFindUsingSameInstance() {
        HotDeskNumber number = new HotDeskNumber(1);
        HotDesk hotDesk = new HotDesk(number);
        repository.save(hotDesk);

        HotDesk foundDesk = repository.findByNumber(number);
        assertNotNull(foundDesk);
        assertEquals(hotDesk, foundDesk);
    }

    @Test
    void testFindById() {
        HotDeskNumber number = new HotDeskNumber(2);
        HotDesk hotDesk = new HotDesk(number);
        repository.save(hotDesk);
        HotDeskId id = hotDesk.getId();
        HotDesk foundDesk = repository.findById(id);
        assertNotNull(foundDesk);
        assertEquals(id, foundDesk.getId());
    }

    @Test
    void testFindFirstAvailable() {
        // Create two hot desks.
        HotDeskNumber number1 = new HotDeskNumber(3);
        HotDesk hotDesk1 = new HotDesk(number1);
        // Mark hotDesk1 as unavailable on the given date.
        HotDeskReservationDate date = new HotDeskReservationDate("2050-02-24");
        hotDesk1.reserve(new HotDeskReservation(hotDesk1.getId(), "user1", date));

        HotDeskNumber number2 = new HotDeskNumber(4);
        HotDesk hotDesk2 = new HotDesk(number2);

        repository.save(hotDesk1);
        repository.save(hotDesk2);

        HotDesk available = repository.findFirstAvailable(date);
        assertNotNull(available);
        assertEquals(hotDesk2, available);
    }

    @Test
    void testClearRepository() {
        HotDeskNumber number = new HotDeskNumber(5);
        HotDesk hotDesk = new HotDesk(number);
        repository.save(hotDesk);
        repository.clear();
        HotDesk found = repository.findByNumber(number);
        assertNull(found, "Repository should be empty after clear");
    }
}
