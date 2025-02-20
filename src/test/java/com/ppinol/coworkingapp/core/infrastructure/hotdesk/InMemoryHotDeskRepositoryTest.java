package com.ppinol.coworkingapp.core.infrastructure.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryHotDeskRepository;
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
}
