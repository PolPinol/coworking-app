package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.domain.events.MembershipCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MembershipProjection {

    private final InMemoryMembershipReadModel readModelRepository;

    public MembershipProjection(InMemoryMembershipReadModel readModelRepository) {
        this.readModelRepository = readModelRepository;
    }

    @EventListener
    public void onMembershipCreated(MembershipCreatedEvent event) {
        String userId = event.getPayload().userId();
        String membershipId = event.getAggregateId().value();
        readModelRepository.saveMembership(userId, membershipId);
    }
}
