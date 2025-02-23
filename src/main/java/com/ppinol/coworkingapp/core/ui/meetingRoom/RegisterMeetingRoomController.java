package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.ppinol.coworkingapp.core.application.meetingRoom.RegisterMeetingRoomCommand;
import com.ppinol.coworkingapp.core.application.meetingRoom.RegisterMeetingRoomCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registerMeetingRoom")
public class RegisterMeetingRoomController {

    private final RegisterMeetingRoomCommandHandler commandHandler;

    public RegisterMeetingRoomController(RegisterMeetingRoomCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody RegisterMeetingRoomDTO dto) {
        commandHandler.handle(
                new RegisterMeetingRoomCommand(dto.name(), dto.capacity())
        );
        return ResponseEntity.ok().build();
    }
}
