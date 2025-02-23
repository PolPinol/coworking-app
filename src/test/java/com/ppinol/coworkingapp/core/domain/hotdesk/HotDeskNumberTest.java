package com.ppinol.coworkingapp.core.domain.hotdesk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotDeskNumberTest {

    @Test
    void testValidNumber() {
        HotDeskNumber number = new HotDeskNumber(5);
        assertEquals("HotDeskNumber{number=5}", number.toString());
    }

    @Test
    void testNegativeNumberThrowsException() {
        Exception exception = assertThrows(InvalidHotDeskNumberException.class, () -> {
            new HotDeskNumber(-10);
        });
        assertEquals("Desk number must be positive", exception.getMessage());
    }
}
