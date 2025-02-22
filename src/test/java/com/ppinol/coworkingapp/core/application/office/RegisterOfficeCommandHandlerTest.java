package com.ppinol.coworkingapp.core.application.office;

import com.ppinol.coworkingapp.core.domain.office.Office;
import com.ppinol.coworkingapp.core.domain.office.OfficeNumber;
import com.ppinol.coworkingapp.core.domain.office.OfficeRepository;
import com.ppinol.coworkingapp.core.domain.office.OfficeLeasePeriod;
import com.ppinol.coworkingapp.core.domain.office.OfficeStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterOfficeCommandHandlerTest {

    @Test
    void testRegisterOfficeSuccess() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand("101", "24", "Active");

        handler.handle(command);

        verify(repository, times(1)).save(any(Office.class));
    }

    @Test
    void testRegisterOfficeDuplicateThrowsException() {
        OfficeRepository repository = mock(OfficeRepository.class);

        when(repository.findByNumber(any())).thenReturn(new Office(new OfficeNumber("101"),
                new OfficeLeasePeriod("24"), new OfficeStatus("Active")));

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand("101", "24", "Active");

        Exception exception = assertThrows(DuplicatedOfficeException.class, () -> handler.handle(command));
        assertEquals("Office already exists", exception.getMessage());
    }

    @Test
    void testRegisterOfficeInvalidLeasePeriodThrowsException() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand("102", "invalid", "Active");

        Exception exception = assertThrows(RuntimeException.class, () -> handler.handle(command));
        assertEquals("Office lease period must be a number", exception.getMessage());
    }

    @Test
    void testRegisterOfficeInvalidOfficeNumberThrowsException() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand("abc", "24", "Active");

        Exception exception = assertThrows(RuntimeException.class, () -> handler.handle(command));
        assertEquals("Office number must be an integer", exception.getMessage());
    }

    @Test
    void testRegisterOfficeInvalidStatusThrowsException() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand("103", "24", "Busy");

        Exception exception = assertThrows(RuntimeException.class, () -> handler.handle(command));
        assertEquals("Invalid office status: Busy", exception.getMessage());
    }
}
