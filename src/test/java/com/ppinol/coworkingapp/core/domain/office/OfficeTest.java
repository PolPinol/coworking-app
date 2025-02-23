package com.ppinol.coworkingapp.core.domain.office;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class OfficeTest {

    @Test
    void testOfficeCreation() {
        OfficeNumber number = new OfficeNumber(101);
        OptionalInt intValue = OptionalInt.of(24);
        OfficeLeasePeriod leasePeriod = new OfficeLeasePeriod(intValue);
        Optional<String> statusValue = Optional.of(OfficeStatus.ACTIVE);
        OfficeStatus status = new OfficeStatus(statusValue);
        Office office = new Office(number, leasePeriod, status);

        assertNotNull(office.getId());
        assertEquals(number, office.getNumber());
        assertEquals(leasePeriod, office.getLeasePeriod());
        assertEquals(status, office.getStatus());
        assertNotNull(office.getCreatedAt());
        assertNotNull(office.getUpdatedAt());
    }
}
