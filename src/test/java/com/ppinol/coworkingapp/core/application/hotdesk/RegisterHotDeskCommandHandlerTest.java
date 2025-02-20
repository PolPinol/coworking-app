package com.ppinol.coworkingapp.core.application.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterHotDeskCommandHandlerTest {

    @Test
    void testRegisterNewHotDesk() {
        HotDeskRepository repository = mock(HotDeskRepository.class);
        when(repository.findByNumber(any(HotDeskNumber.class))).thenReturn(null);

        RegisterHotDeskCommandHandler handler = new RegisterHotDeskCommandHandler(repository);
        RegisterHotDeskCommand command = new RegisterHotDeskCommand("1");

        handler.handle(command);

        verify(repository, times(1)).save(any(HotDesk.class));
    }

    @Test
    void testDuplicateHotDeskNumber() {
        HotDeskRepository repository = mock(HotDeskRepository.class);
        HotDesk existingDesk = new HotDesk(new HotDeskNumber("1"));
        when(repository.findByNumber(any(HotDeskNumber.class))).thenReturn(existingDesk);

        RegisterHotDeskCommandHandler handler = new RegisterHotDeskCommandHandler(repository);
        RegisterHotDeskCommand command = new RegisterHotDeskCommand("1");

        assertThrows(DuplicatedHotDeskException.class, () -> handler.handle(command));
    }
}
