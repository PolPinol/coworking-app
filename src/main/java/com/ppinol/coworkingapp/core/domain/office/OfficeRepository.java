package com.ppinol.coworkingapp.core.domain.office;

public interface OfficeRepository {
    void save(Office office);
    Office findByNumber(OfficeNumber number);
    void clear();
}
