package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.infrastructure.InMemoryMembershipCreditsReadModel;
import com.ppinol.coworkingapp.membership.infrastructure.InMemoryUserMembershipReadModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GetFullMembershipSummaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryUserMembershipReadModel userMembershipReadModel;

    @Autowired
    private InMemoryMembershipCreditsReadModel membershipCreditsReadModel;

    private final String validUserId = "123e4567-e89b-12d3-a456-426614174100";
    private final String validMembershipId = "membership-001";

    @BeforeEach
    void setUp() {
        userMembershipReadModel.clear();
        membershipCreditsReadModel.clear();
    }

    @Test
    void testGetMembershipSummarySuccess() throws Exception {
        this.userMembershipReadModel.saveMembership(validUserId, validMembershipId);

        int credits = 100;
        this.membershipCreditsReadModel.addCredits(validMembershipId, credits);

        mockMvc.perform(get("/memberships/{userId}", validUserId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(validMembershipId)))
                .andExpect(jsonPath("$.user_id", is(validUserId)))
                .andExpect(jsonPath("$.total_credits", is(credits)));
    }

    @Test
    void testGetMembershipSummaryInvalidInput() throws Exception {
        mockMvc.perform(get("/memberships/{userId}", "")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetMembershipSummaryMembershipNotFound() throws Exception {
        String nonExistentUserId = "123e4567-e89b-12d3-a456-426614174999";
        mockMvc.perform(get("/memberships")
                        .param("userId", nonExistentUserId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
