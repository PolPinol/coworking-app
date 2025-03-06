package com.ppinol.coworkingapp.membership.domain.events;

import com.ppinol.coworkingapp.membership.domain.Id;
import com.ppinol.coworkingapp.membership.domain.model.Credits;
import com.ppinol.coworkingapp.membership.domain.model.MembershipId;
import com.ppinol.coworkingapp.membership.domain.model.PackageId;

import java.util.Date;

public class PackageSubscribedEvent extends Event<PackageSubscribedEventPayload> {

    public static final String TYPE = "PackageSubscribedEvent";

    private PackageSubscribedEvent(Id aggregateId, PackageSubscribedEventPayload payload) {
        super(aggregateId, TYPE, payload);
    }

    public static PackageSubscribedEvent with(MembershipId membershipId, PackageId packageId, Credits credits,
                                              Date startDate, Date endDate) {
        PackageSubscribedEventPayload payload = new PackageSubscribedEventPayload(
                packageId.value(),
                credits.value(),
                startDate,
                endDate
        );
        return new PackageSubscribedEvent(membershipId, payload);
    }
}