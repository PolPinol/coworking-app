package com.ppinol.coworkingapp.core.domain;

import java.util.List;

public interface EventPublisher {
    void publish(List<Event<?>> events);
}
