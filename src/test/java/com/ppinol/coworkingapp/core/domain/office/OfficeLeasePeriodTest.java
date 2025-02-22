package com.ppinol.coworkingapp.core.domain.office;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OfficeLeasePeriodTest {

    @Test
    void testValidLeasePeriod() {
        OfficeLeasePeriod leasePeriod = new OfficeLeasePeriod("24");
        OfficeLeasePeriod leasePeriod2 = new OfficeLeasePeriod("24");
        assertEquals(leasePeriod, leasePeriod2);
    }

    @Test
    void testNullOrEmptyLeasePeriodUsesDefault() {
        OfficeLeasePeriod leasePeriodNull = new OfficeLeasePeriod(null);
        OfficeLeasePeriod leasePeriodEmpty = new OfficeLeasePeriod("");
        OfficeLeasePeriod defaultLeasePeriod = new OfficeLeasePeriod("12"); // default is 12

        assertEquals(defaultLeasePeriod, leasePeriodNull);
        assertEquals(defaultLeasePeriod, leasePeriodEmpty);
    }

    @Test
    void testInvalidLeasePeriodNonNumeric() {
        Exception exception = assertThrows(InvalidOfficeLeasePeriodException.class, () -> {
            new OfficeLeasePeriod("abc");
        });
        assertEquals("Office lease period must be a number", exception.getMessage());
    }

    @Test
    void testNegativeLeasePeriodThrowsException() {
        Exception exception = assertThrows(InvalidOfficeLeasePeriodException.class, () -> {
            new OfficeLeasePeriod("-5");
        });
        assertEquals("Office lease period must be positive", exception.getMessage());
    }
}
