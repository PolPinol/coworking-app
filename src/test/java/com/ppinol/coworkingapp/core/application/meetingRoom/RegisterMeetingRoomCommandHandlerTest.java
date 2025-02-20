package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class RegisterMeetingRoomCommandHandlerTest {

    @Test
    void testRegisterMeetingRoomSuccess() {
        MeetingRoomRepository repository = mock(MeetingRoomRepository.class);
        RegisterMeetingRoomCommandHandler handler = new RegisterMeetingRoomCommandHandler(repository);
        RegisterMeetingRoomCommand command = new RegisterMeetingRoomCommand("Conference", "25");

        handler.handle(command);

        verify(repository, times(1)).save(any(MeetingRoom.class));
    }

    @Test
    void testRegisterMeetingRoomWithInvalidCapacityThrowsException() {
        MeetingRoomRepository repository = mock(MeetingRoomRepository.class);
        RegisterMeetingRoomCommandHandler handler = new RegisterMeetingRoomCommandHandler(repository);
        RegisterMeetingRoomCommand command = new RegisterMeetingRoomCommand("Conference", "invalid");

        assertThrows(RuntimeException.class, () -> handler.handle(command));
    }

    @Test
    void testDuplicateMeetingRoom() {
        MeetingRoomRepository repository = mock(MeetingRoomRepository.class);
        MeetingRoom existingRoom = new MeetingRoom(new MeetingRoomName("Conference"), new MeetingRoomCapacity("25"));
        when(repository.findByName(any(MeetingRoomName.class))).thenReturn(existingRoom);

        RegisterMeetingRoomCommandHandler handler = new RegisterMeetingRoomCommandHandler(repository);
        RegisterMeetingRoomCommand command = new RegisterMeetingRoomCommand("Conference", "30");

        assertThrows(DuplicatedMeetingRoomException.class, () -> handler.handle(command));
    }
}
