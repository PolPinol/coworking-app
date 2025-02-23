package com.ppinol.coworkingapp.core.application.meetingRoom;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskRepository;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomId;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomCapacity;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReserveMeetingRoomCommandHandlerTest {

    @Test
    void testMeetingRoomNotFound() {
        // Arrange
        MeetingRoomRepository meetingRoomRepository = mock(MeetingRoomRepository.class);
        HotDeskRepository hotDeskRepository = mock(HotDeskRepository.class);
        ReserveMeetingRoomCommandHandler handler = new ReserveMeetingRoomCommandHandler(meetingRoomRepository, hotDeskRepository);
        ReserveMeetingRoomCommand command = new ReserveMeetingRoomCommand("nonexistent-room", "2050-02-24", 10, 2, "user-123");

        // When findById is called, return null to simulate not found.
        when(meetingRoomRepository.findById(any(MeetingRoomId.class))).thenReturn(null);

        // Act & Assert
        assertThrows(MeetingRoomNotFoundException.class, () -> handler.handle(command));
    }

    @Test
    void testReserveMeetingRoomWithHotDeskAvailable() {
        // Arrange
        MeetingRoomRepository meetingRoomRepository = mock(MeetingRoomRepository.class);
        HotDeskRepository hotDeskRepository = mock(HotDeskRepository.class);
        ReserveMeetingRoomCommandHandler handler = new ReserveMeetingRoomCommandHandler(meetingRoomRepository, hotDeskRepository);

        // Create a dummy meeting room.
        MeetingRoomId roomId = new MeetingRoomId("room-123");
        MeetingRoom meetingRoom = new MeetingRoom(new MeetingRoomName("Conference Room"), new MeetingRoomCapacity(10));
        when(meetingRoomRepository.findById(any(MeetingRoomId.class))).thenReturn(meetingRoom);

        // Create a dummy hot desk.
        HotDesk hotDesk = new HotDesk(new HotDeskNumber(1));
        when(hotDeskRepository.findFirstAvailable(any(HotDeskReservationDate.class))).thenReturn(hotDesk);

        ReserveMeetingRoomCommand command = new ReserveMeetingRoomCommand(
                roomId.id(),      // meetingRoomId from command as string
                "2050-02-24",     // date
                10,               // hour
                2,                // duration
                "user-123"        // userId
        );

        // Act
        handler.handle(command);

        // Assert: Verify that the meeting room was saved and that the hot desk was reserved and saved.
        verify(meetingRoomRepository, times(1)).save(meetingRoom);
        verify(hotDeskRepository, times(1)).save(hotDesk);
    }

    @Test
    void testReserveMeetingRoomWithoutHotDeskAvailable() {
        // Arrange
        MeetingRoomRepository meetingRoomRepository = mock(MeetingRoomRepository.class);
        HotDeskRepository hotDeskRepository = mock(HotDeskRepository.class);
        ReserveMeetingRoomCommandHandler handler = new ReserveMeetingRoomCommandHandler(meetingRoomRepository, hotDeskRepository);

        // Create a dummy meeting room.
        MeetingRoomId roomId = new MeetingRoomId("room-456");
        MeetingRoom meetingRoom = new MeetingRoom(new MeetingRoomName("Board Room"), new MeetingRoomCapacity(30));
        when(meetingRoomRepository.findById(any(MeetingRoomId.class))).thenReturn(meetingRoom);

        // Simulate no hot desk available.
        when(hotDeskRepository.findFirstAvailable(any(HotDeskReservationDate.class))).thenReturn(null);

        ReserveMeetingRoomCommand command = new ReserveMeetingRoomCommand(
                roomId.id(),
                "2050-02-24",
                11,
                1,
                "user-456"
        );

        // Act
        handler.handle(command);

        // Assert: Only the meeting room should be saved; hotDeskRepository.save should never be called.
        verify(meetingRoomRepository, times(1)).save(meetingRoom);
        verify(hotDeskRepository, never()).save(any(HotDesk.class));
    }
}
