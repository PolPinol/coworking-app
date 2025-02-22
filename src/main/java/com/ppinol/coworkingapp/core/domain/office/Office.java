package com.ppinol.coworkingapp.core.domain.office;

import java.util.Date;

public class Office {
    private final OfficeId id;
    private final OfficeNumber number;
    private final OfficeLeasePeriod leasePeriod;
    private final OfficeStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public Office(OfficeNumber number, OfficeLeasePeriod leasePeriod, OfficeStatus status) {
        this.id = OfficeId.generate();
        this.number = number;
        this.leasePeriod = leasePeriod;
        this.status = status;
        this.createdAt = new Date();
        this.updatedAt = new Date();
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
