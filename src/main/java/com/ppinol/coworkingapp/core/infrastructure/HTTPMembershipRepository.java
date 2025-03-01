package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.membership.*;
import org.springframework.stereotype.Repository;

@Repository
public class HTTPMembershipRepository implements MembershipRepository {

    @Override
    public ResponsePaymentMembershipDTO pay(RequestPaymentMembershipDTO request) {
        MembershipId id = new MembershipId("1234");
        Credits credits = new Credits(1234);
        return new ResponsePaymentMembershipDTO(id, credits);
    }
}
