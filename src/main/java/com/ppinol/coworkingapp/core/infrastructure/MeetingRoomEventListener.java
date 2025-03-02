package com.ppinol.coworkingapp.core.infrastructure;

import com.ppinol.coworkingapp.core.domain.reservation.meetingRoom.MeetingRoomWasReservedEvent;
import com.ppinol.coworkingapp.core.application.hotdesk.ReserveHotDeskCommand;
import com.ppinol.coworkingapp.core.application.hotdesk.ReserveHotDeskCommandHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MeetingRoomEventListener {

    private final ReserveHotDeskCommandHandler commandHandler;

    public MeetingRoomEventListener(ReserveHotDeskCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @EventListener
    public void onMeetingRoomWasReservedEvent(MeetingRoomWasReservedEvent event) {
        String userId = event.getPayload().userId();
        String date = event.getPayload().date().toString();

        commandHandler.handle(
                new ReserveHotDeskCommand(userId, date, true /* courtesy */)
        );
    }
}
