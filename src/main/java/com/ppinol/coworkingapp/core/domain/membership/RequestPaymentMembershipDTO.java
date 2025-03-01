package com.ppinol.coworkingapp.core.domain.membership;

import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservationDate;

public record RequestPaymentMembershipDTO(UserId userId, HotDeskReservationDate date) {}
