package com.ppinol.coworkingapp.core.domain;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.hotdesk.InvalidHotDeskNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HotDeskNumberTest {

    @Test
    void testValidNumber() {
        HotDeskNumber number = new HotDeskNumber("5");
        assertEquals("DeskNumber{number=5}", number.toString());
    }

    @Test
    void testInvalidFormatThrowsException() {
        Exception exception = assertThrows(InvalidHotDeskNumberException.class, () -> {
            new HotDeskNumber("abc");
        });
        assertEquals("Desk number must be a number", exception.getMessage());
    }

    @Test
    void testNegativeNumberThrowsException() {
        Exception exception = assertThrows(InvalidHotDeskNumberException.class, () -> {
            new HotDeskNumber("-10");
        });
        assertEquals("Desk number must be positive", exception.getMessage());
    }
}
