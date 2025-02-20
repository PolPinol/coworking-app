package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHotDeskRepositoryTest {

    @Test
    void testSaveAndFindUsingSameInstance() {
        InMemoryHotDeskRepository repository = new InMemoryHotDeskRepository();
        HotDeskNumber number = new HotDeskNumber("1");
        HotDesk hotDesk = new HotDesk(number);
        repository.save(hotDesk);

        HotDesk foundDesk = repository.findByNumber(number);
        assertNotNull(foundDesk);
        assertEquals(hotDesk, foundDesk);
    }

    @Test
    void testSaveAndFindUsingDifferentInstance() {
        InMemoryHotDeskRepository repository = new InMemoryHotDeskRepository();
        HotDeskNumber number = new HotDeskNumber("1");
        HotDesk hotDesk = new HotDesk(number);
        repository.save(hotDesk);

        HotDeskNumber sameValueNumber = new HotDeskNumber("1");
        HotDesk foundDesk = repository.findByNumber(sameValueNumber);

        assertNotNull(foundDesk, "The repository did not find the hot desk using a different instance. "
                + "This test exposes the bug that '==' is used instead of '.equals()'.");
    }
}
