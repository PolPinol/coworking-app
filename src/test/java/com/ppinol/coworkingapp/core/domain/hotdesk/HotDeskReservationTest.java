package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotDeskReservationTest {

    @Test
    void testReservationCreation() {
        HotDeskId hotDeskId = HotDeskId.generate();
        String userId = "user1";
        HotDeskReservationDate date = new HotDeskReservationDate("2025-02-24");

        HotDeskReservation reservation = new HotDeskReservation(hotDeskId, userId, date);

        assertNotNull(reservation.getReservationId(), "Reservation ID should not be null");
        assertEquals(hotDeskId, reservation.getHotDeskId(), "HotDeskId should match");
        assertEquals(userId, reservation.getUserId(), "UserId should match");
        assertEquals(date, reservation.getDate(), "Dates should match");
        assertNotNull(reservation.getStatus(), "Status should not be null");
        assertNotNull(reservation.getCreatedAt(), "CreatedAt should not be null");
        assertNotNull(reservation.getUpdatedAt(), "UpdatedAt should not be null");
        assertEquals(reservation.getCreatedAt(), reservation.getUpdatedAt(), "CreatedAt and UpdatedAt should be equal upon creation");
    }

    @Test
    void testOverlapsWithSameDate() {
        HotDeskId hotDeskId = HotDeskId.generate();
        HotDeskReservationDate date = new HotDeskReservationDate("2025-02-24");

        HotDeskReservation reservation1 = new HotDeskReservation(hotDeskId, "user1", date);
        HotDeskReservation reservation2 = new HotDeskReservation(hotDeskId, "user2", date);

        assertTrue(reservation1.overlapsWith(reservation2), "Reservations on the same date should overlap");
    }

    @Test
    void testOverlapsWithDifferentDate() {
        HotDeskId hotDeskId = HotDeskId.generate();
        HotDeskReservationDate date1 = new HotDeskReservationDate("2025-02-24");
        HotDeskReservationDate date2 = new HotDeskReservationDate("2025-02-25");

        HotDeskReservation reservation1 = new HotDeskReservation(hotDeskId, "user1", date1);
        HotDeskReservation reservation2 = new HotDeskReservation(hotDeskId, "user2", date2);

        assertFalse(reservation1.overlapsWith(reservation2), "Reservations on different dates should not overlap");
    }

    @Test
    void testToStringContainsData() {
        HotDeskId hotDeskId = HotDeskId.generate();
        HotDeskReservationDate date = new HotDeskReservationDate("2025-02-24");
        HotDeskReservation reservation = new HotDeskReservation(hotDeskId, "user1", date);

        String str = reservation.toString();
        assertTrue(str.contains(hotDeskId.toString()), "toString should contain HotDeskId");
        assertTrue(str.contains("user1"), "toString should contain userId");
        assertTrue(str.contains(date.toString()), "toString should contain the reservation date");
    }
}
