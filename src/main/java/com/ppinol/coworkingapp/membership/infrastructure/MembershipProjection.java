package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.domain.events.MembershipCreatedEvent;
import com.ppinol.coworkingapp.membership.domain.events.PackageSubscribedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MembershipProjection {

    private final InMemoryUserMembershipReadModel userMembershipReadModel;
    private final InMemoryMembershipCreditsReadModel membershipCreditsReadModel;

    public MembershipProjection(InMemoryUserMembershipReadModel userMembershipReadModel,
                                InMemoryMembershipCreditsReadModel membershipCreditsReadModel) {
        this.userMembershipReadModel = userMembershipReadModel;
        this.membershipCreditsReadModel = membershipCreditsReadModel;
    }

    @EventListener
    public void onMembershipCreated(MembershipCreatedEvent event) {
        String userId = event.getPayload().userId();
        String membershipId = event.getAggregateId().value();
        this.userMembershipReadModel.saveMembership(userId, membershipId);
    }

    @EventListener
    public void onPackageSubscribed(PackageSubscribedEvent event) {
        String membershipId = event.getAggregateId().value();
        int credits = event.getPayload().credits();
        this.membershipCreditsReadModel.addCredits(membershipId, credits);
    }
}
