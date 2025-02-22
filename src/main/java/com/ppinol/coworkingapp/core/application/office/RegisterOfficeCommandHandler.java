package com.ppinol.coworkingapp.core.application.office;

import com.ppinol.coworkingapp.core.domain.office.*;
import org.springframework.stereotype.Service;

@Service
public class RegisterOfficeCommandHandler {

    private final OfficeRepository repository;

    public RegisterOfficeCommandHandler(OfficeRepository repository) {
        this.repository = repository;
    }

    public void handle(RegisterOfficeCommand command) {
        OfficeNumber number = new OfficeNumber(command.number());

        if (this.repository.findByNumber(number) != null) {
            throw new DuplicatedOfficeException("Office already exists");
        }

        OfficeLeasePeriod leasePeriod = new OfficeLeasePeriod(command.leasePeriod());
        OfficeStatus status = new OfficeStatus(command.status());
        Office office = new Office(number, leasePeriod, status);
        this.repository.save(office);
    }
}
