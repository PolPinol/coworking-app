package com.ppinol.coworkingapp.core.domain.hotdesk;

import java.util.Date;

public class HotDesk {
    private final HotDeskId id;
    private final HotDeskNumber number;
    private HotDeskStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public HotDesk(HotDeskNumber number) {
        this.id = HotDeskId.generate();
        this.number = number;
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

    public void setStatus(HotDeskStatus status) {
        this.status = status;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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