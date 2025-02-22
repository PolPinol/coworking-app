package com.ppinol.coworkingapp.core.ui.office;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OfficeDTOTest {

    @Test
    void testValidDTO() {
        OfficeDTO dto = new OfficeDTO("101", "24", "Active");
        assertEquals("101", dto.number());
        assertEquals("24", dto.leasePeriod());
        assertEquals("Active", dto.status());
    }

    @Test
    void testNullNumberThrowsException() {
        Exception exception = assertThrows(InvalidRegisterOfficeInputException.class, () -> {
            new OfficeDTO(null, "24", "Active");
        });
        assertEquals("name should not be null", exception.getMessage());
    }

    @Test
    void testEmptyNumberThrowsException() {
        Exception exception = assertThrows(InvalidRegisterOfficeInputException.class, () -> {
            new OfficeDTO("   ", "24", "Active");
        });
        assertEquals("number should not be empty", exception.getMessage());
    }
}
