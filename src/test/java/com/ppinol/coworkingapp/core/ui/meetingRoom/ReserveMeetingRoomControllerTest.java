package com.ppinol.coworkingapp.core.ui.meetingRoom;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.domain.hotdesk.reservation.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryMeetingRoomRepository;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryHotDeskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReserveMeetingRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryMeetingRoomRepository meetingRoomRepository;

    @Autowired
    private InMemoryHotDeskRepository hotDeskRepository;

    @BeforeEach
    void setUp() {
        meetingRoomRepository.clear();
        hotDeskRepository.clear();
    }

    @Test
    void testReserveMeetingRoomSuccess() throws Exception {
        // Register a meeting room.
        String meetingRoomJson = "{\"name\":\"Room A\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(meetingRoomJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room A"));
        assertThat(room).isNotNull();

        // Register a HotDesk to simulate availability for a courtesy assignment.
        String hotDeskJson = "{\"number\":1}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(hotDeskJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Reserve the meeting room for a future date (e.g., "2025-02-24").
        String reserveJson = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-24\",\"hour\":10,\"duration\":2,\"userId\":\"123e4567-e89b-12d3-a456-426614174000\"}",
                room.getId().id()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the complimentary HotDesk was assigned:
        HotDeskReservationDate reservationDate = new HotDeskReservationDate("2025-02-24");
        assertThat(hotDeskRepository.findFirstAvailable(reservationDate)).isNull();
    }

    @Test
    void testReserveMeetingRoomMeetingRoomNotFound() throws Exception {
        String reserveJson = "{\"meetingRoomId\":\"non-existent-id\",\"date\":\"2025-02-24\",\"hour\":10,\"duration\":2,\"userId\":\"user-id\"}";
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testReserveMeetingRoomOverlapping() throws Exception {
        String meetingRoomJson = "{\"name\":\"Room A\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(meetingRoomJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room A"));
        assertThat(room).isNotNull();

        String reserveJson1 = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-24\",\"hour\":10,\"duration\":2,\"userId\":\"user-1\"}",
                room.getId().id()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String reserveJson2 = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-24\",\"hour\":11,\"duration\":2,\"userId\":\"user-2\"}",
                room.getId().id()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void testReserveMeetingRoomInvalidInput() throws Exception {
        String reserveJson = "{\"meetingRoomId\":\"id\",\"date\":\"2025-02-24\",\"duration\":2,\"userId\":\"user-id\"}";
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
