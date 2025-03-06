package com.ppinol.coworkingapp.membership.domain.model;

import com.ppinol.coworkingapp.membership.domain.events.MembershipCreatedEvent;
import com.ppinol.coworkingapp.membership.domain.events.PackageSubscribedEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MembershipState {
    private final MembershipId membershipId;
    private final UserId userId;
    private final Active active;
    private final Date createdAt;
    private final List<Package> packages;

    MembershipState(MembershipId membershipId, UserId userId, Active active, Date createdAt, List<Package> packages) {
        this.membershipId = membershipId;
        this.userId = userId;
        this.active = active;
        this.createdAt = createdAt;
        this.packages = packages;
    }

    public static MembershipState empty() {
        return new MembershipState(MembershipId.empty(),
                                   UserId.empty(),
                                   Active.empty(),
                                   new Date(),
                                   Collections.emptyList());
    }

    public MembershipState withMembershipCreated(MembershipCreatedEvent event) {
        return new MembershipState(
                (MembershipId) event.getAggregateId(),
                UserId.create(event.getPayload().userId()),
                Active.create(),
                event.getPayload().createdAt(),
                new ArrayList<>()
        );
    }

    public MembershipState withPackageSubscribed(PackageSubscribedEvent event) {
        Package newPackage = Package.from(
                event.getPayload().packageId(),
                event.getPayload().credits(),
                event.getPayload().startDate(),
                event.getPayload().endDate()
        );
        List<Package> newPackages = new ArrayList<>(this.packages);
        newPackages.add(newPackage);
        return new MembershipState(
                membershipId,
                userId,
                active,
                createdAt,
                newPackages
        );
    }

    public MembershipId getMembershipId() {
        return membershipId;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public boolean isEmpty() {
        return membershipId.isEmpty();
    }
}
