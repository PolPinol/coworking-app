package com.ppinol.coworkingapp.core.domain.office;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OfficeNumberTest {

    @Test
    void testValidOfficeNumber() {
        OfficeNumber officeNumber = new OfficeNumber("101");
        OfficeNumber officeNumber2 = new OfficeNumber("101");
        assertEquals(officeNumber, officeNumber2);
    }

    @Test
    void testInvalidOfficeNumberNonNumeric() {
        Exception exception = assertThrows(InvalidOfficeNumberException.class, () -> {
            new OfficeNumber("abc");
        });
        assertEquals("Office number must be an integer", exception.getMessage());
    }

    @Test
    void testNegativeOfficeNumberThrowsException() {
        Exception exception = assertThrows(InvalidOfficeNumberException.class, () -> {
            new OfficeNumber("-1");
        });
        assertEquals("Office number must be positive", exception.getMessage());
    }

    @Test
    void testNullOrEmptyOfficeNumberThrowsException() {
        Exception exception = assertThrows(InvalidOfficeNumberException.class, () -> {
            new OfficeNumber(null);
        });
        assertEquals("Office number must be an integer", exception.getMessage());

        Exception exception2 = assertThrows(InvalidOfficeNumberException.class, () -> {
            new OfficeNumber("");
        });
        assertEquals("Office number must be an integer", exception2.getMessage());
    }
}
