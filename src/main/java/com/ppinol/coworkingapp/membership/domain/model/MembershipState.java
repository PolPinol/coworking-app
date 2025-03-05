package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.domain.events.MembershipCreatedEvent;

import java.util.Date;

public class MembershipState {
    private final MembershipId membershipId;
    private final UserId userId;
    private final Active active;
    private final Date createdAt;

    MembershipState(MembershipId membershipId, UserId userId, Active active, Date createdAt) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.active = active;
        this.createdAt = createdAt;
    }

    public static MembershipState empty() {
        return new MembershipState(MembershipId.empty(),
                                   UserId.empty(),
                                   Active.empty(),
                                   new Date());
    }

    public MembershipState withMembershipCreated(MembershipCreatedEvent event) {
        return new MembershipState(
                (MembershipId) event.getAggregateId(),
                UserId.create(event.getPayload().userId()),
                Active.create(),
                event.getPayload().createdAt()
        );
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }
}
