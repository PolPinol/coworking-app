package com.ppinol.coworkingapp.core.domain.office;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OfficeTest {

    @Test
    void testOfficeCreation() {
        OfficeNumber number = new OfficeNumber("101");
        OfficeLeasePeriod leasePeriod = new OfficeLeasePeriod("24");
        OfficeStatus status = new OfficeStatus("Active");
        Office office = new Office(number, leasePeriod, status);

        assertNotNull(office.getId());
        assertEquals(number, office.getNumber());
        assertEquals(leasePeriod, office.getLeasePeriod());
        assertEquals(status, office.getStatus());
        assertNotNull(office.getCreatedAt());
        assertNotNull(office.getUpdatedAt());
    }
}
