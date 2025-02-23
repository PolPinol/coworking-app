package com.ppinol.coworkingapp.core.application.office;

import com.ppinol.coworkingapp.core.domain.InvalidStatusException;
import com.ppinol.coworkingapp.core.domain.office.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterOfficeCommandHandlerTest {

    @Test
    void testRegisterOfficeSuccess() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        OptionalInt leasePeriod = OptionalInt.of(101);
        Optional<String> status = Optional.of(OfficeStatus.ACTIVE);
        RegisterOfficeCommand command = new RegisterOfficeCommand(101, leasePeriod, status);

        handler.handle(command);

        verify(repository, times(1)).save(any(Office.class));
    }

    @Test
    void testRegisterOfficeDuplicateThrowsException() {
        OfficeRepository repository = mock(OfficeRepository.class);

        OptionalInt leasePeriod = OptionalInt.of(24);
        Optional<String> status = Optional.of(OfficeStatus.ACTIVE);

        when(repository.findByNumber(any())).thenReturn(new Office(new OfficeNumber(101),
                new OfficeLeasePeriod(leasePeriod), new OfficeStatus(status)));

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand(101, leasePeriod, status);

        Exception exception = assertThrows(DuplicatedOfficeException.class, () -> handler.handle(command));
        assertEquals("Office already exists", exception.getMessage());
    }

    @Test
    void testRegisterOfficeInvalidLeasePeriodThrowsException() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        OptionalInt leasePeriod = OptionalInt.of(-1);
        Optional<String> status = Optional.of(OfficeStatus.ACTIVE);

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand(102, leasePeriod, status);

        Exception exception = assertThrows(InvalidOfficeLeasePeriodException.class, () -> handler.handle(command));
        assertEquals("Office lease period must be positive", exception.getMessage());
    }

    @Test
    void testRegisterOfficeDefaultLeasePeriod() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        Optional<String> status = Optional.of(OfficeStatus.ACTIVE);

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand(102, OptionalInt.empty(), status);

        handler.handle(command);

        verify(repository, times(1)).save(any(Office.class));
    }

    @Test
    void testRegisterOfficeInvalidStatusThrowsException() {
        OfficeRepository repository = mock(OfficeRepository.class);
        when(repository.findByNumber(any())).thenReturn(null);

        OptionalInt leasePeriod = OptionalInt.of(101);
        Optional<String> status = Optional.of("Busy");

        RegisterOfficeCommandHandler handler = new RegisterOfficeCommandHandler(repository);
        RegisterOfficeCommand command = new RegisterOfficeCommand(103, leasePeriod, status);

        Exception exception = assertThrows(InvalidStatusException.class, () -> handler.handle(command));
        assertEquals("Invalid status: Busy", exception.getMessage());
    }
}
