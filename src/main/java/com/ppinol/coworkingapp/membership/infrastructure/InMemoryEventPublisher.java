package com.ppinol.coworkingapp.membership.infrastructure;

import com.ppinol.coworkingapp.membership.domain.events.Event;
import com.ppinol.coworkingapp.membership.domain.events.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher eventPublisher;
    private final List<Event<?>> publishedEvents = new ArrayList<>();

    public InMemoryEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publish(List<Event<?>> events) {
        this.publishedEvents.addAll(events);
        events.forEach(this.eventPublisher::publishEvent);
    }

    public boolean hasPublishedEvent(String type, String aggregateId) {
        return publishedEvents.stream()
                .anyMatch(event -> event.getType().equals(type)
                        && event.getAggregateId().value().equals(aggregateId));
    }
}
