package com.ppinol.coworkingapp.membership.domain;

import com.ppinol.coworkingapp.membership.domain.model.Membership;

public interface MembershipRepository {
    void save(Membership membership);
    void clear();
}
