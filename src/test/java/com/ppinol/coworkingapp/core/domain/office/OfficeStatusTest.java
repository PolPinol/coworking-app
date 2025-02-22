package com.ppinol.coworkingapp.core.domain.office;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OfficeStatusTest {

    @Test
    void testValidStatusActive() {
        OfficeStatus status = new OfficeStatus("Active");
        assertEquals("Active", status.getStatus());
    }

    @Test
    void testValidStatusInactive() {
        OfficeStatus status = new OfficeStatus("Inactive");
        assertEquals("Inactive", status.getStatus());
    }

    @Test
    void testNullOrEmptyStatusUsesDefault() {
        OfficeStatus statusNull = new OfficeStatus(null);
        OfficeStatus statusEmpty = new OfficeStatus("");
        // default is "Active"
        assertEquals("Active", statusNull.getStatus());
        assertEquals("Active", statusEmpty.getStatus());
    }

    @Test
    void testInvalidStatusThrowsException() {
        Exception exception = assertThrows(InvalidOfficeStatusException.class, () -> {
            new OfficeStatus("Busy");
        });
        assertEquals("Invalid office status: Busy", exception.getMessage());
    }
}
