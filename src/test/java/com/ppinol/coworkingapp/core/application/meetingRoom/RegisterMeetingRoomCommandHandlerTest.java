package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterMeetingRoomCommandHandlerTest {

    @Test
    void testRegisterMeetingRoomSuccess() {
        // Arrange: Create a mock repository and a valid command.
        MeetingRoomRepository repository = mock(MeetingRoomRepository.class);
        RegisterMeetingRoomCommandHandler handler = new RegisterMeetingRoomCommandHandler(repository);
        RegisterMeetingRoomCommand command = new RegisterMeetingRoomCommand("Conference", 25);

        // Act: Handle the command.
        handler.handle(command);

        // Assert: Verify that the repository's save method was called exactly once.
        verify(repository, times(1)).save(any(MeetingRoom.class));
    }

    @Test
    void testRegisterMeetingRoomWithInvalidCapacityThrowsException() {
        // Arrange: Create a command with an invalid capacity.
        MeetingRoomRepository repository = mock(MeetingRoomRepository.class);
        RegisterMeetingRoomCommandHandler handler = new RegisterMeetingRoomCommandHandler(repository);
        RegisterMeetingRoomCommand command = new RegisterMeetingRoomCommand("Conference", -3);

        // Act & Assert: Expect a NumberFormatException when handling the command.
        assertThrows(InvalidMeetingRoomCapacityException.class, () -> handler.handle(command));
    }

    @Test
    void testDuplicateMeetingRoomThrowsException() {
        // Arrange: Set up the repository to return an existing room when searched by name.
        MeetingRoomRepository repository = mock(MeetingRoomRepository.class);
        MeetingRoom existingRoom = new MeetingRoom(new MeetingRoomName("Conference"), new MeetingRoomCapacity(25));
        when(repository.findByName(any(MeetingRoomName.class))).thenReturn(existingRoom);

        RegisterMeetingRoomCommandHandler handler = new RegisterMeetingRoomCommandHandler(repository);
        RegisterMeetingRoomCommand command = new RegisterMeetingRoomCommand("Conference", 30);

        // Act & Assert: Expect a DuplicatedMeetingRoomException when trying to register a room with the same name.
        assertThrows(DuplicatedMeetingRoomException.class, () -> handler.handle(command));
    }
}
