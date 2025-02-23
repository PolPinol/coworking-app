package com.ppinol.coworkingapp.core.domain.office;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class OfficeLeasePeriodTest {

    @Test
    void testValidLeasePeriod() {
        OptionalInt value = OptionalInt.of(24);
        OfficeLeasePeriod leasePeriod = new OfficeLeasePeriod(value);
        OfficeLeasePeriod leasePeriod2 = new OfficeLeasePeriod(value);
        assertEquals(leasePeriod, leasePeriod2);
    }

    @Test
    void tesEmptyLeasePeriodUsesDefault() {
        OptionalInt value = OptionalInt.empty();
        OfficeLeasePeriod leasePeriodEmpty = new OfficeLeasePeriod(value);
        OfficeLeasePeriod defaultLeasePeriod = new OfficeLeasePeriod(OptionalInt.of(OfficeLeasePeriod.DEFAULT_LEASE_PERIOD));

        assertEquals(defaultLeasePeriod, leasePeriodEmpty);
    }

    @Test
    void testNegativeLeasePeriodThrowsException() {
        Exception exception = assertThrows(InvalidOfficeLeasePeriodException.class, () -> {
            OptionalInt value = OptionalInt.of(-5);
            new OfficeLeasePeriod(value);
        });
        assertEquals("Office lease period must be positive", exception.getMessage());
    }
}
