package com.ppinol.coworkingapp.core.ui.office;

import com.ppinol.coworkingapp.core.application.office.RegisterOfficeCommand;
import com.ppinol.coworkingapp.core.application.office.RegisterOfficeCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registerOffice")
public class RegisterOfficeController {

    private final RegisterOfficeCommandHandler commandHandler;

    public RegisterOfficeController(RegisterOfficeCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public ResponseEntity<Void> handle(@RequestBody OfficeDTO officeDTO) {
        commandHandler.handle(
                new RegisterOfficeCommand(officeDTO.number(), officeDTO.leasePeriod(), officeDTO.status())
        );
        return ResponseEntity.ok().build();
    }
}
