package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.ppinol.coworkingapp.core.application.hotdesk.ReserveHotDeskCommand;
import com.ppinol.coworkingapp.core.application.hotdesk.ReserveHotDeskCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserveHotDesk")
public class ReserveHotDeskController {

    private final ReserveHotDeskCommandHandler commandHandler;

    public ReserveHotDeskController(ReserveHotDeskCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody ReserveHotDeskDTO dto) {
        commandHandler.handle(
                new ReserveHotDeskCommand(dto.userId(), dto.date(), false /* courtesy */)
        );
        return ResponseEntity.ok().build();
    }
}
