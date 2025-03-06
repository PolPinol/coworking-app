package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.domain.model.Membership;
import com.ppinol.coworkingapp.membership.domain.MembershipRepository;
import com.ppinol.coworkingapp.membership.domain.events.Event;
import com.ppinol.coworkingapp.membership.domain.model.MembershipId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryMembershipRepository implements MembershipRepository {
    private final List<Event<?>> eventStore = new ArrayList<>();

    @Override
    public void save(Membership membership) {
        this.eventStore.addAll(membership.getUncommittedEvents());
    }

    @Override
    public Membership findById(MembershipId membershipId) {
        List<Event<?>> eventsForAggregate = eventStore.stream()
                .filter(event -> event.getAggregateId().equals(membershipId))
                .collect(Collectors.toList());

        if (eventsForAggregate.isEmpty()) {
            return null;
        }

        return Membership.from(eventsForAggregate);
    }

    // Clear for testing purposes.
    public void clear() {
        this.eventStore.clear();
    }
}
