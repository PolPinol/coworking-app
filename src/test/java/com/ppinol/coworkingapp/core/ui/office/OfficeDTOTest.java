package com.ppinol.coworkingapp.core.ui.office;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OfficeDTOTest {

    @Test
    void testValidDTO() {
        OfficeDTO dto = new OfficeDTO(101, 24, "Active");
        assertEquals(101, dto.number());
        assertEquals(24, dto.leasePeriod().orElseThrow());
        assertEquals("Active", dto.status().orElseThrow());
    }

    @Test
    void testNullNumberThrowsException() {
        Exception exception = assertThrows(InvalidRegisterOfficeInputException.class, () -> {
            new OfficeDTO(null, 24, "Active");
        });
        assertEquals("name should not be null", exception.getMessage());
    }

    @Test
    void testNullLeasePeriodNotThrowsException() {
        OfficeDTO dto = new OfficeDTO(101, null, "Active");
        assertEquals(101, dto.number());
        assertFalse(dto.leasePeriod().isPresent());
        assertEquals("Active", dto.status().orElseThrow());
    }

    @Test
    void testNullStatusNotThrowsException() {
        OfficeDTO dto = new OfficeDTO(101, 24, null);
        assertEquals(101, dto.number());
        assertEquals(24, dto.leasePeriod().orElseThrow());
        assertFalse(dto.status().isPresent());
    }
}
