package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.ppinol.coworkingapp.core.application.hotdesk.RegisterHotDeskCommand;
import com.ppinol.coworkingapp.core.application.hotdesk.RegisterHotDeskCommandHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/registerHotDesk")
public class RegisterHotDeskController {

    private final RegisterHotDeskCommandHandler commandHandler;


    public RegisterHotDeskController(RegisterHotDeskCommandHandler registerHotDeskCommandHandler) {
        this.commandHandler = registerHotDeskCommandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody HotDeskDTO dto) {
        commandHandler.handle(new RegisterHotDeskCommand(dto.number()));
        return ResponseEntity.ok().build();
    }
}
