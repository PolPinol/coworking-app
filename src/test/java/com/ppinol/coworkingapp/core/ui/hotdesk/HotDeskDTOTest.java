package com.ppinol.coworkingapp.core.ui.hotdesk;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotDeskDTOTest {

    @Test
    void testValidNumber() {
        HotDeskDTO dto = new HotDeskDTO(42);
        assertEquals(42, dto.number());
    }

    @Test
    void testNullNumberThrowsException() {
        Exception exception = assertThrows(
                InvalidRegisterHotDeskInputException.class,
                () -> new HotDeskDTO(null)
        );
        assertEquals("Missing 'number' field in request body.", exception.getMessage());
    }
}
