package com.ppinol.coworkingapp.membership.domain;

import com.ppinol.coworkingapp.membership.domain.model.Membership;
import com.ppinol.coworkingapp.membership.domain.model.MembershipId;

public interface MembershipRepository {
    Membership findById(MembershipId membershipId);
    void save(Membership membership);
}
