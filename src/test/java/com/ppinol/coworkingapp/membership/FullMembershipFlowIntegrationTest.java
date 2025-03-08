package com.ppinol.coworkingapp.membership;

import com.ppinol.coworkingapp.membership.domain.model.Membership;
import com.ppinol.coworkingapp.membership.domain.model.MembershipId;
import com.ppinol.coworkingapp.membership.infrastructure.InMemoryMembershipCreditsReadModel;
import com.ppinol.coworkingapp.membership.infrastructure.InMemoryMembershipRepository;
import com.ppinol.coworkingapp.membership.infrastructure.InMemoryUserMembershipReadModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FullMembershipFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryMembershipRepository membershipRepository;

    @Autowired
    private InMemoryUserMembershipReadModel userMembershipReadModel;

    @Autowired
    private InMemoryMembershipCreditsReadModel membershipCreditsReadModel;

    @BeforeEach
    void setUp() {
        membershipRepository.clear();
        userMembershipReadModel.clear();
        membershipCreditsReadModel.clear();
    }

    @Test
    void testFullMembershipFlow() throws Exception {
        // 1. Create a new membership for a user
        String userId = "123e4567-e89b-12d3-a456-426614174999";
        String createMembershipJson = String.format("{\"userId\":\"%s\"}", userId);

        mockMvc.perform(post("/createMembership")
                        .content(createMembershipJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Retrieve the membershipId from the read model (projection)
        Map<String, String> projection = userMembershipReadModel.getProjection();
        String membershipId = projection.get(userId);
        assertThat(membershipId).isNotNull();

        // 2. Register a package (i.e. subscribe a package with prepaid credits) for the membership
        int credits = 20;
        int year = 2025;
        int month = 2;
        String registerPackageJson = String.format(
                "{\"membershipId\":\"%s\", \"credits\":%d, \"year\":%d, \"month\":%d}",
                membershipId, credits, year, month
        );

        mockMvc.perform(post("/registerPackage")
                        .content(registerPackageJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the membership aggregate has been updated with a new package.
        Membership membership = membershipRepository.findById(MembershipId.from(membershipId));
        assertThat(membership.getState().getPackages()).isNotEmpty();

        // 3. Retrieve the full membership summary
        mockMvc.perform(get("/memberships/{userId}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(membershipId)))
                .andExpect(jsonPath("$.user_id", is(userId)))
                .andExpect(jsonPath("$.total_credits", is(credits)));
    }
}
