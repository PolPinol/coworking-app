package com.ppinol.coworkingapp.core.domain.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.OverlappingHotDeskReservationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HotDesk {
    private final HotDeskId id;
    private final HotDeskNumber number;
    private HotDeskStatus status;
    private final Date createdAt;
    private Date updatedAt;

    private final List<HotDeskReservation> reservations;

    public HotDesk(HotDeskNumber number) {
        this.id = HotDeskId.generate();
        this.number = number;
        this.status = HotDeskStatus.create();
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.reservations = new ArrayList<>();
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

    public void markAsUpdated() {
        this.updatedAt = new Date();
    }

    public void reserve(HotDeskReservation newReservation) {
        for (HotDeskReservation existingReservation : this.reservations) {
            if (existingReservation.overlapsWith(newReservation)) {
                throw new OverlappingHotDeskReservationException("Reservation overlaps with an existing reservation");
            }
        }

        this.reservations.add(newReservation);
    }

    public boolean isAvailable(HotDeskReservationDate date) {
        for (HotDeskReservation reservation : this.reservations) {
            if (reservation.getDate().isSameDay(date)) {
                return false;
            }
        }
        return true;
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