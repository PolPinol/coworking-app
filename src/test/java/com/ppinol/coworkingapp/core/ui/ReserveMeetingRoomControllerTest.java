package com.ppinol.coworkingapp.core.ui;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryMeetingRoomRepository;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryHotDeskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
        String meetingRoomJson = "{\"name\":\"Room A\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(meetingRoomJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room A"));
        assertThat(room).isNotNull();

        String hotDeskJson = "{\"number\":1}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(hotDeskJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<HotDesk> availableHotDesks = hotDeskRepository.findAll();
        assertThat(availableHotDesks).isNotEmpty();

        String userId = "123e4567-e89b-12d3-a456-426614174000";
        String reserveJson = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-24\",\"hour\":10,\"duration\":2,\"userId\":\"%s\"}",
                room.getId().value(), userId
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        assertThat(meetingRoomRepository.findById(room.getId())).isNotNull();

        HotDesk assignedHotDesk = hotDeskRepository.findAll().stream()
                .findFirst()
                .orElse(null);

        assertThat(assignedHotDesk).isNotNull();
        assertThat(assignedHotDesk.getNumber()).isEqualTo(new HotDeskNumber(1));
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
        String meetingRoomJson = "{\"name\":\"Room B\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(meetingRoomJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room B"));
        assertThat(room).isNotNull();

        String reserveJson1 = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-24\",\"hour\":10,\"duration\":2,\"userId\":\"user-2\"}",
                room.getId().value()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        String reserveJson2 = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-24\",\"hour\":11,\"duration\":2,\"userId\":\"user-3\"}",
                room.getId().value()
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

    @Test
    void testReserveMeetingRoomInvalidDateFormat() throws Exception {
        String meetingRoomJson = "{\"name\":\"Room B\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(meetingRoomJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room B"));
        assertThat(room).isNotNull();

        String reserveJson = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"24-02-2025\",\"hour\":10,\"duration\":2,\"userId\":\"123e4567-e89b-12d3-a456-426614174001\"}",
                room.getId().value()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReserveMeetingRoomHourOutOfRange() throws Exception {
        String meetingRoomJson = "{\"name\":\"Room C\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(meetingRoomJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room C"));
        assertThat(room).isNotNull();

        String reserveJsonNegativeHour = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-25\",\"hour\":-1,\"duration\":2,\"userId\":\"123e4567-e89b-12d3-a456-426614174002\"}",
                room.getId().value()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJsonNegativeHour)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        String reserveJsonExceedHour = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-25\",\"hour\":24,\"duration\":2,\"userId\":\"123e4567-e89b-12d3-a456-426614174003\"}",
                room.getId().value()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJsonExceedHour)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReserveMeetingRoomDurationOutOfRange() throws Exception {
        String meetingRoomJson = "{\"name\":\"Room D\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(meetingRoomJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room D"));
        assertThat(room).isNotNull();

        String reserveJsonShortDuration = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-26\",\"hour\":10,\"duration\":0,\"userId\":\"123e4567-e89b-12d3-a456-426614174004\"}",
                room.getId().value()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJsonShortDuration)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        String reserveJsonLongDuration = String.format(
                "{\"meetingRoomId\":\"%s\",\"date\":\"2025-02-26\",\"hour\":10,\"duration\":13,\"userId\":\"123e4567-e89b-12d3-a456-426614174005\"}",
                room.getId().value()
        );
        mockMvc.perform(post("/reserveMeetingRoom")
                        .content(reserveJsonLongDuration)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReserveMeetingRoomReservationForTodayInvalidHour() throws Exception {
        int currentHour = LocalTime.now().getHour();
        if (currentHour < 23) {
            String meetingRoomJson = "{\"name\":\"Room E\",\"capacity\":10}";
            mockMvc.perform(post("/registerMeetingRoom")
                            .content(meetingRoomJson)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
            MeetingRoom room = meetingRoomRepository.findByName(new MeetingRoomName("Room E"));
            assertThat(room).isNotNull();

            String today = LocalDate.now().toString();
            String reserveJson = String.format(
                    "{\"meetingRoomId\":\"%s\",\"date\":\"%s\",\"hour\":%d,\"duration\":2,\"userId\":\"123e4567-e89b-12d3-a456-426614174006\"}",
                    room.getId().value(),
                    today,
                    currentHour
            );
            mockMvc.perform(post("/reserveMeetingRoom")
                            .content(reserveJson)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }
}
