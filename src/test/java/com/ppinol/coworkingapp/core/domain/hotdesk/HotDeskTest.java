package com.ppinol.coworkingapp.core.domain.hotdesk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotDeskTest {

    @Test
    void testHotDeskCreation() {
        HotDeskNumber number = new HotDeskNumber("2");
        HotDesk hotDesk = new HotDesk(number);

        assertNotNull(hotDesk.getId());
        assertEquals(number, hotDesk.getNumber());
        assertTrue(hotDesk.getStatus().isActive());
        assertNotNull(hotDesk.getCreatedAt());
        assertNotNull(hotDesk.getUpdatedAt());
    }
}
