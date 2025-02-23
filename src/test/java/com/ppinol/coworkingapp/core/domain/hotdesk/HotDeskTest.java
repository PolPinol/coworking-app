package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.OverlappingHotDeskReservationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotDeskTest {

    @Test
    void testHotDeskCreation() {
        HotDeskNumber number = new HotDeskNumber(2);
        HotDesk hotDesk = new HotDesk(number);

        assertNotNull(hotDesk.getId());
        assertEquals(number, hotDesk.getNumber());
        assertTrue(hotDesk.getStatus().isActive());
        assertNotNull(hotDesk.getCreatedAt());
        assertNotNull(hotDesk.getUpdatedAt());
    }

    @Test
    void testReserveAddsReservation() {
        HotDesk hotDesk = new HotDesk(new HotDeskNumber(3));
        HotDeskReservationDate date = new HotDeskReservationDate("2025-02-24");
        HotDeskReservation reservation = new HotDeskReservation(hotDesk.getId(), "user1", date);

        // Before reserving, the hot desk is available on the given date.
        assertTrue(hotDesk.isAvailable(date));

        // Reserve the hot desk.
        hotDesk.reserve(reservation);

        // After reserving, the hot desk should not be available for the same date.
        assertFalse(hotDesk.isAvailable(date));
    }

    @Test
    void testReserveThrowsForOverlappingReservation() {
        HotDesk hotDesk = new HotDesk(new HotDeskNumber(4));
        HotDeskReservationDate date = new HotDeskReservationDate("2025-02-24");
        HotDeskReservation reservation1 = new HotDeskReservation(hotDesk.getId(), "user1", date);
        HotDeskReservation reservation2 = new HotDeskReservation(hotDesk.getId(), "user2", date);

        // Reserve once.
        hotDesk.reserve(reservation1);

        // A second reservation for the same date should throw an exception.
        assertThrows(OverlappingHotDeskReservationException.class, () -> {
            hotDesk.reserve(reservation2);
        });
    }

    @Test
    void testIsAvailableForDifferentDates() {
        HotDesk hotDesk = new HotDesk(new HotDeskNumber(5));
        HotDeskReservationDate date1 = new HotDeskReservationDate("2025-02-24");
        HotDeskReservationDate date2 = new HotDeskReservationDate("2025-02-25");
        HotDeskReservation reservation = new HotDeskReservation(hotDesk.getId(), "user1", date1);

        // Reserve for date1.
        hotDesk.reserve(reservation);

        // The hot desk should not be available on date1, but should be available on date2.
        assertFalse(hotDesk.isAvailable(date1));
        assertTrue(hotDesk.isAvailable(date2));
    }

    @Test
    void testToStringContainsData() {
        HotDeskNumber number = new HotDeskNumber(7);
        HotDesk hotDesk = new HotDesk(number);
        String str = hotDesk.toString();

        // Check that the string representation contains the hot desk's number and active status.
        assertTrue(str.contains(number.toString()));
        assertTrue(str.contains("Active")); // Assumes that active status prints as "Active"
    }
}
