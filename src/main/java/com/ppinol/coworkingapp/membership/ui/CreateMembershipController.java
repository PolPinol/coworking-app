package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.application.CreateMembershipCommand;
import com.ppinol.coworkingapp.membership.application.CreateMembershipCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createMembership")
public class CreateMembershipController {

    private final CreateMembershipCommandHandler commandHandler;


    public CreateMembershipController(CreateMembershipCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody CreateMembershipDTO dto) {
        commandHandler.handle(new CreateMembershipCommand(dto.userId()));
        return ResponseEntity.ok().build();
    }
}
