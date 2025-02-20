package com.ppinol.coworkingapp.core.domain;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotDeskStatusTest {

    @Test
    void testCreateStatus() {
        HotDeskStatus status = HotDeskStatus.create();
        assertTrue(status.isActive());
        assertFalse(status.isAvailable());
    }

    @Test
    void testReleaseStatus() {
        HotDeskStatus status = HotDeskStatus.release();
        assertTrue(status.isAvailable());
        assertFalse(status.isActive());
    }
}
