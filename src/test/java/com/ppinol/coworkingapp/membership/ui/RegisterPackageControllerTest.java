package com.ppinol.coworkingapp.membership.ui;

import com.ppinol.coworkingapp.membership.domain.model.Membership;
import com.ppinol.coworkingapp.membership.domain.model.MembershipId;
import com.ppinol.coworkingapp.membership.infrastructure.InMemoryMembershipRepository;
import com.ppinol.coworkingapp.membership.infrastructure.InMemoryMembershipReadModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RegisterPackageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryMembershipRepository membershipRepository;

    @Autowired
    private InMemoryMembershipReadModel readModelRepository;

    private String membershipId;

    @BeforeEach
    void setUp() throws Exception {
        this.membershipRepository.clear();
        this.readModelRepository.clear();

        String userId = "123e4567-e89b-12d3-a456-426614174010";
        String membershipJson = String.format("{\"userId\":\"%s\"}", userId);

        mockMvc.perform(post("/createMembership")
                        .content(membershipJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Map<String, String> store = readModelRepository.getStore();
        membershipId = store.get(userId);
    }

    @Test
    void testRegisterPackageSuccess() throws Exception {
        String json = String.format(
                "{\"membershipId\":\"%s\", \"credits\":10, \"year\":2025, \"month\":2}",
                membershipId
        );

        mockMvc.perform(post("/registerPackage")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Membership membership = membershipRepository.findById(MembershipId.from(membershipId));
        assertThat(membership.getState().getPackages()).isNotEmpty();
    }

    @Test
    void testRegisterPackageInvalidInput() throws Exception {
        // Missing membershipId
        String json = "{\"credits\":10, \"year\":2025, \"month\":2}";
        mockMvc.perform(post("/registerPackage")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        // Credits must be greater than 0
        json = String.format("{\"membershipId\":\"%s\", \"credits\":0, \"year\":2025, \"month\":2}", membershipId);
        mockMvc.perform(post("/registerPackage")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterPackageMembershipNotFound() throws Exception {
        String json = "{\"membershipId\":\"non-existent-id\", \"credits\":10, \"year\":2025, \"month\":2}";
        mockMvc.perform(post("/packages")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
