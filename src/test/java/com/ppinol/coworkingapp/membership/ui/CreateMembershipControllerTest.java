package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.infrastructure.InMemoryMembershipReadModelRepository;
import com.ppinol.coworkingapp.membership.infrastructure.InMemoryMembershipRepository;
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
class CreateMembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryMembershipRepository membershipRepository;

    @Autowired
    private InMemoryMembershipReadModelRepository readModelRepository;

    @BeforeEach
    void setUp() {
        membershipRepository.clear();
        readModelRepository.clear();
    }

    @Test
    void testCreateMembershipSuccess() throws Exception {
        String userId = "123e4567-e89b-12d3-a456-426614174000";
        String json = String.format("{\"userId\":\"%s\"}", userId);

        mockMvc.perform(post("/createMembership")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify the read model now contains a membership for the user
        assertThat(readModelRepository.exists(userId)).isTrue();
    }

    @Test
    void testCreateMembershipDuplicate() throws Exception {
        String userId = "123e4567-e89b-12d3-a456-426614174001";
        String json = String.format("{\"userId\":\"%s\"}", userId);

        // First membership creation should succeed
        mockMvc.perform(post("/createMembership")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Second attempt for the same userId should return 409 Conflict
        mockMvc.perform(post("/createMembership")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void testCreateMembershipInvalidInput() throws Exception {
        // Missing userId field
        String json = "{}";
        mockMvc.perform(post("/createMembership")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Empty userId value
        json = "{\"userId\":\"\"}";
        mockMvc.perform(post("/createMembership")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
