package com.ppinol.coworkingapp.membership.domain.events;

import com.ppinol.coworkingapp.membership.domain.Id;
import com.ppinol.coworkingapp.membership.domain.model.MembershipId;
import com.ppinol.coworkingapp.membership.domain.model.UserId;

import java.util.Date;

public class MembershipCreatedEvent extends Event<MembershipCreatedEventPayload> {

    public static final String TYPE = "MembershipCreatedEvent";

    private MembershipCreatedEvent(Id aggregateId, MembershipCreatedEventPayload payload) {
        super(aggregateId, TYPE, payload);
    }

    public static MembershipCreatedEvent with(MembershipId membershipId, UserId userId, Date createdAt) {
        MembershipCreatedEventPayload payload = new MembershipCreatedEventPayload(
                userId.value(),
                createdAt
        );
        return new MembershipCreatedEvent(membershipId, payload);
    }
}
