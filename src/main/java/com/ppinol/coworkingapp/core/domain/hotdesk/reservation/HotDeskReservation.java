package com.ppinol.coworkingapp.core.domain.hotdesk.reservation;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskId;

import java.util.Date;

public class HotDeskReservation {

    private final HotDeskReservationId hotDeskReservationId;
    private final HotDeskId hotDeskId;
    private final String userId;
    private final HotDeskReservationDate date;
    private final HotDeskReservationStatus status;
    private final Date createdAt;
    private Date updatedAt;

    public HotDeskReservation(HotDeskId hotDeskId, String userId, HotDeskReservationDate date) {
        this.hotDeskReservationId = HotDeskReservationId.generate();
        this.hotDeskId = hotDeskId;
        this.userId = userId;
        this.date = date;
        this.status = HotDeskReservationStatus.create();
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public HotDeskReservationId getReservationId() {
        return hotDeskReservationId;
    }

    public HotDeskId getHotDeskId() {
        return hotDeskId;
    }

    public String getUserId() {
        return userId;
    }

    public HotDeskReservationDate getDate() {
        return date;
    }

    public HotDeskReservationStatus getStatus() {
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

    public boolean overlapsWith(HotDeskReservation newReservation) {
        return this.date.equals(newReservation.getDate());
    }

    @Override
    public String toString() {
        return "HotDeskReservation{" +
                "hotDeskReservationId=" + hotDeskReservationId +
                ", hotDeskId=" + hotDeskId +
                ", userId='" + userId + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
