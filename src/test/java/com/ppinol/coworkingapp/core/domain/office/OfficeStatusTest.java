package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.InvalidStatusException;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OfficeStatusTest {

    @Test
    void testValidStatusActive() {
        Optional<String> value = Optional.of(OfficeStatus.ACTIVE);
        OfficeStatus status = new OfficeStatus(value);
        assertTrue(status.isActive());
    }

    @Test
    void testValidStatusInactive() {
        Optional<String> value = Optional.of(OfficeStatus.INACTIVE);
        OfficeStatus status = new OfficeStatus(value);
        assertFalse(status.isActive());
    }

    @Test
    void testEmptyStatusUsesDefault() {
        Optional<String> value = Optional.empty();
        OfficeStatus statusNull = new OfficeStatus(value);
        assertTrue(statusNull.isActive());
    }

    @Test
    void testInvalidStatusThrowsException() {
        Exception exception = assertThrows(InvalidStatusException.class, () -> {
            Optional<String> value = Optional.of("Busy");
            new OfficeStatus(value);
        });
        assertEquals("Invalid status: Busy", exception.getMessage());
    }
}
