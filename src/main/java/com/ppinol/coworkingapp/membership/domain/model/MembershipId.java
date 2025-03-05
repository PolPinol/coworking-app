package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.domain.Id;

public class MembershipId extends Id {

    MembershipId(String id) {
        super(id);
    }

    public static MembershipId generate() {
        return new MembershipId(generateId());
    }

    public static MembershipId empty() {
        return new MembershipId("");
    }
}
