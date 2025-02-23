package com.ppinol.coworkingapp.core.infrastructure.office;

import com.ppinol.coworkingapp.core.domain.office.Office;
import com.ppinol.coworkingapp.core.domain.office.OfficeLeasePeriod;
import com.ppinol.coworkingapp.core.domain.office.OfficeNumber;
import com.ppinol.coworkingapp.core.domain.office.OfficeStatus;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryOfficeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryOfficeRepositoryTest {

    private InMemoryOfficeRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryOfficeRepository();
        repository.clear();
    }

    @Test
    void testSaveAndFindOffice() {
        OfficeNumber number = new OfficeNumber(101);
        OptionalInt intValue = OptionalInt.of(24);
        OfficeLeasePeriod leasePeriod = new OfficeLeasePeriod(intValue);
        Optional<String> statusValue = Optional.of(OfficeStatus.ACTIVE);
        OfficeStatus status = new OfficeStatus(statusValue);
        Office office = new Office(number, leasePeriod, status);

        repository.save(office);
        Office found = repository.findByNumber(new OfficeNumber(101));
        assertNotNull(found);
        assertEquals(number, found.getNumber());
    }
}
