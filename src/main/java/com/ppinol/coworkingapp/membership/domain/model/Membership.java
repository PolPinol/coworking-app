package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.domain.events.Event;
import com.ppinol.coworkingapp.membership.domain.events.EventSourcedEntity;
import com.ppinol.coworkingapp.membership.domain.events.MembershipCreatedEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Membership extends EventSourcedEntity {
    private MembershipState state;

    private Membership(List<Event<?>> stream) {
        super(stream);

        if (stream.isEmpty()) {
            this.state = MembershipState.empty();
        }
    }

    public static Membership empty() {
        return new Membership(new ArrayList<>());
    }

    public static Membership from(List<Event<?>> stream) {
        return new Membership(stream);
    }

    public static Membership create(UserId userId) {
        MembershipId membershipId = MembershipId.generate();
        Membership membership = Membership.empty();
        membership.applyCreatedEvent(membershipId, userId, new Date());
        return membership;
    }

    private void applyCreatedEvent(MembershipId id, UserId userId, Date createdAt) {
        this.apply(MembershipCreatedEvent.with(id, userId, createdAt));
    }

    @Override
    protected void when(Event<?> e) {
        switch (e.getType()) {
            case MembershipCreatedEvent.TYPE:
                whenMembershipCreated((MembershipCreatedEvent) e);
                break;
            default:
                throw new IllegalArgumentException("Unknown event type");
        }
    }

    private void whenMembershipCreated(MembershipCreatedEvent event) {
        this.state = this.state.withMembershipCreated(event);
    }

    public MembershipState getState() {
        return state;
    }
}
