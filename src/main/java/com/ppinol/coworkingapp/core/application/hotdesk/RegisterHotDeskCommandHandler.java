package com.ppinol.coworkingapp.core.application.hotdesk;

import com.ppinol.coworkingapp.core.domain.EventPublisher;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterHotDeskCommandHandler {

    private final HotDeskRepository repository;
    private final EventPublisher eventPublisher;

    public RegisterHotDeskCommandHandler(HotDeskRepository repository, EventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(RegisterHotDeskCommand command) {
        HotDeskNumber number = new HotDeskNumber(command.number());

        if (this.repository.findByNumber(number) != null) {
            throw new DuplicatedHotDeskException("HotDesk number already exists");
        }

        HotDesk hotDesk = new HotDesk(number.value());
        this.repository.save(hotDesk);

        this.eventPublisher.publish(hotDesk.releaseEvents());
    }
}
