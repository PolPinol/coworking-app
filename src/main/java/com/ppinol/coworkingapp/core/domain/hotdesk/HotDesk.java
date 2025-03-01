package com.ppinol.coworkingapp.core.domain.hotdesk;

import java.util.Date;

public class HotDesk {
    private final HotDeskId id;
    private final HotDeskNumber number;
    private HotDeskStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public HotDesk(int number) {
        this.id = HotDeskId.generate();
        this.number = new HotDeskNumber(number);
        this.status = HotDeskStatus.create();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public HotDeskId getId() {
        return id;
    }

    public HotDeskNumber getNumber() {
        return number;
    }

    public HotDeskStatus getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void markAsUpdated() {
        this.updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "HotDesk{" +
                "id=" + id +
                ", number=" + number +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}