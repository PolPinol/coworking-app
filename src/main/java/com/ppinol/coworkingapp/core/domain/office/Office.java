package com.ppinol.coworkingapp.core.domain.office;

import com.ppinol.coworkingapp.core.domain.AggregateRoot;

import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

public class Office extends AggregateRoot {
    private final OfficeId id;
    private final OfficeNumber number;
    private final OfficeLeasePeriod leasePeriod;
    private final OfficeStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public Office(int number, OptionalInt leasePeriod, Optional<String> status) {
        this.id = OfficeId.generate();
        this.number = new OfficeNumber(number);
        this.leasePeriod = new OfficeLeasePeriod(leasePeriod);
        this.status = new OfficeStatus(status);
        this.createdAt = new Date();
        this.updatedAt = new Date();

        this.recordEvent(OfficeWasRegisteredEvent.from(this));
    }

    public OfficeId getId() {
        return id;
    }

    public OfficeNumber getNumber() {
        return number;
    }

    public OfficeLeasePeriod getLeasePeriod() {
        return leasePeriod;
    }

    public OfficeStatus getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
