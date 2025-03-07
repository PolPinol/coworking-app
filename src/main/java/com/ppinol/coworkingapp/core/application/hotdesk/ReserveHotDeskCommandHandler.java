package com.ppinol.coworkingapp.core.application.hotdesk;

import com.ppinol.coworkingapp.core.domain.EventPublisher;
import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.membership.MembershipRepository;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskRepository;
import com.ppinol.coworkingapp.core.domain.membership.RequestGetInformationFromMembershipDTO;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservationRepository;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.UserAlreadyHasReservationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReserveHotDeskCommandHandler {

    private final HotDeskRepository hotDeskRepository;
    private final HotDeskReservationRepository reservationRepository;
    private final MembershipRepository membershipRepository;
    private final EventPublisher eventPublisher;

    public ReserveHotDeskCommandHandler(HotDeskRepository hotDeskRepository,
                                        HotDeskReservationRepository hotDeskReservationRepository,
                                        MembershipRepository membershipRepository,
                                        EventPublisher eventPublisher) {
        this.hotDeskRepository = hotDeskRepository;
        this.reservationRepository = hotDeskReservationRepository;
        this.membershipRepository = membershipRepository;
        this.eventPublisher = eventPublisher;
    }

    public void handle(ReserveHotDeskCommand command) {
        try {
            HotDeskReservationDate date = new HotDeskReservationDate(command.date());
            UserId userId = new UserId(command.userId());

            if (this.reservationRepository.find(userId, date) != null) {
                throw new UserAlreadyHasReservationException("User already has a reservation for date " + command.date());
            }

            Optional<HotDesk> availableDesk = this.hotDeskRepository.findAll().stream()
                    .filter(hotDesk -> this.reservationRepository.isHotDeskAvailable(hotDesk.getId(), date))
                    .findFirst();

            if (availableDesk.isEmpty()) {
                throw new NoAvailableHotDeskException("No available hot desk for date " + command.date());
            }

            HotDeskReservation newReservation = new HotDeskReservation(availableDesk.get().getId(), userId, command.date());
            this.reservationRepository.save(newReservation);

            RequestGetInformationFromMembershipDTO request = new RequestGetInformationFromMembershipDTO(userId, date);
            this.membershipRepository.getInformation(request);

            this.eventPublisher.publish(newReservation.releaseEvents());
        } catch (RuntimeException ex) {
            if (!command.courtesy()) throw ex;
        }
    }
}
