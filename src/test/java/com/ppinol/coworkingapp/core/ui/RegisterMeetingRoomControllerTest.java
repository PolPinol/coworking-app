package com.ppinol.coworkingapp.core.ui;

import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoom;
import com.ppinol.coworkingapp.core.domain.meetingRoom.MeetingRoomName;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryMeetingRoomRepository;
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
class RegisterMeetingRoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryMeetingRoomRepository repository;

    @BeforeEach
    void setUp() {
        repository.clear();
    }

    @Test
    void testRegisterMeetingRoomSuccess() throws Exception {
        String json = "{\"name\":\"Room A\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MeetingRoom room = repository.findByName(new MeetingRoomName("Room A"));
        assertThat(room).isNotNull();
        assertThat(room.getId()).isNotNull();
        assertThat(room.getCreatedAt()).isNotNull();
        assertThat(room.getUpdatedAt()).isNotNull();
        assertThat(room.getStatus().value().isActive()).isTrue();
        assertThat(room.getCapacity().value()).isEqualTo(10);
    }

    @Test
    void testRegisterMeetingRoomInvalidInput() throws Exception {
        String jsonEmptyName = "{\"name\":\"\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(jsonEmptyName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        String jsonInvalidCapacity = "{\"name\":\"Room B\",\"capacity\":0}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(jsonInvalidCapacity)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterMeetingRoomMissingName() throws Exception {
        String json = "{\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterMeetingRoomMissingCapacity() throws Exception {
        String json = "{\"name\":\"Room C\"}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterMeetingRoomNonIntegerCapacity() throws Exception {
        String json = "{\"name\":\"Room D\",\"capacity\":\"ten\"}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterMeetingRoomDuplicate() throws Exception {
        String json = "{\"name\":\"Room A\",\"capacity\":10}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(498));
    }
}
