package com.ppinol.coworkingapp.core.domain.membership;

public interface MembershipRepository {
    ResponsePaymentMembershipDTO pay(RequestPaymentMembershipDTO request);
}
