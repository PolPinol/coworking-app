package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.office.Office;
import com.ppinol.coworkingapp.core.domain.office.OfficeNumber;
import com.ppinol.coworkingapp.core.domain.office.OfficeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOfficeRepository implements OfficeRepository {
    private final List<Office> offices = new ArrayList<>();

    @Override
    public void save(Office office) {
        offices.add(office);
    }

    @Override
    public Office findByNumber(OfficeNumber number) {
        return offices.stream()
                .filter(office -> office.getNumber().equals(number))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void clear() {
        offices.clear();
    }
}
