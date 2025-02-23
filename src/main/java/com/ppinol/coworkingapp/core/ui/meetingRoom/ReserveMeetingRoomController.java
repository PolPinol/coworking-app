package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.ppinol.coworkingapp.core.application.meetingRoom.ReserveMeetingRoomCommand;
import com.ppinol.coworkingapp.core.application.meetingRoom.ReserveMeetingRoomCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserveMeetingRoom")
public class ReserveMeetingRoomController {

    private final ReserveMeetingRoomCommandHandler commandHandler;

    public ReserveMeetingRoomController(ReserveMeetingRoomCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody ReserveMeetingRoomDTO dto) {
        commandHandler.handle(
                new ReserveMeetingRoomCommand(dto.meetingRoomId(), dto.date(), dto.hour(), dto.duration(), dto.userId())
        );
        return ResponseEntity.ok().build();
    }
}