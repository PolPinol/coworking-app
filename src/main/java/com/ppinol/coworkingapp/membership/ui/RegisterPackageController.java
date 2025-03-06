package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.application.RegisterPackageCommand;
import com.ppinol.coworkingapp.membership.application.RegisterPackageCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registerPackage")
public class RegisterPackageController {

    private final RegisterPackageCommandHandler commandHandler;

    public RegisterPackageController(RegisterPackageCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody RegisterPackageDTO dto) {
        this.commandHandler.handle(new RegisterPackageCommand(
                dto.membershipId(), dto.credits(), dto.year(), dto.month()
        ));
        return ResponseEntity.ok().build();
    }
}
