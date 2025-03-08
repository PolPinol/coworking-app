package com.ppinol.coworkingapp.membership.application;

import com.ppinol.coworkingapp.membership.domain.model.Membership;
import com.ppinol.coworkingapp.membership.domain.MembershipRepository;
import com.ppinol.coworkingapp.membership.domain.model.UserId;
import com.ppinol.coworkingapp.membership.domain.events.Event;
import com.ppinol.coworkingapp.membership.domain.events.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CreateMembershipCommandHandler {

    private final MembershipRepository membershipRepository;
    private final UserMembershipReadModel readModel;
    private final EventPublisher eventPublisher;

    public CreateMembershipCommandHandler(MembershipRepository membershipRepository,
                                          UserMembershipReadModel readModel,
                                          EventPublisher eventPublisher) {
        this.membershipRepository = membershipRepository;
        this.readModel = readModel;
        this.eventPublisher = eventPublisher;
    }

    public void handle(CreateMembershipCommand command) {
        UserId userId = UserId.create(command.userId());

        if (this.readModel.exists(command.userId())) {
            throw new DuplicateMembershipException("Duplicated membership for this userId: " + command.userId());
        }

        Membership membership = Membership.create(userId);

        // Get a copy of uncommitted events (without clearing them)
        List<Event<?>> events = membership.getUncommittedEvents();

        // Save the events into the write-side event store
        membershipRepository.save(membership);

        // Publish events to notify subscribers (the projection will update the read model)
        eventPublisher.publish(events);

        // After publishing, clear the uncommitted events from the aggregate
        membership.markEventsAsCommitted();
    }
}
