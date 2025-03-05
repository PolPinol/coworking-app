package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.domain.model.Membership;
import com.ppinol.coworkingapp.membership.domain.MembershipRepository;
import com.ppinol.coworkingapp.membership.domain.events.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryMembershipRepository implements MembershipRepository {
    private final List<Event<?>> eventStore = new ArrayList<>();

    @Override
    public void save(Membership membership) {
        this.eventStore.addAll(membership.getUncommittedEvents());
    }

    @Override
    public void clear() {
        this.eventStore.clear();
    }
}
