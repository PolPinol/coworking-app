package com.ppinol.coworkingapp.core.ui.meetingRoom;

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
    void clearRepository() {
        repository.clear();
    }

    @Test
    void testRegisterMeetingRoomSuccess() throws Exception {
        String json = "{\"name\":\"Room A\",\"capacity\":\"20\"}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        MeetingRoom found = repository.findByName(new MeetingRoomName("Room A"));
        assertThat(found).isNotNull();
        assertThat(found.getName().value()).isEqualTo("Room A");
        assertThat(found.getCapacity().value()).isEqualTo(20);

        assertThat(found.getStatus()).isNotNull();
        assertThat(found.getCreatedAt()).isNotNull();
        assertThat(found.getUpdatedAt()).isNotNull();
    }

    @Test
    void testRegisterMeetingRoomInvalidNameInput() throws Exception {
        String json = "{\"name\":\"\",\"capacity\":\"20\"}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterMeetingRoomInvalidCapacityInput() throws Exception {
        String json = "{\"name\":\"Room A\",\"capacity\":\"0\"}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterMeetingRoomNegativeCapacityInput() throws Exception {
        String json = "{\"name\":\"Room A\",\"capacity\":\"-3\"}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterMeetingRoomDuplicate() throws Exception {
        String json = "{\"name\":\"Room A\",\"capacity\":\"20\"}";
        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/registerMeetingRoom")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
