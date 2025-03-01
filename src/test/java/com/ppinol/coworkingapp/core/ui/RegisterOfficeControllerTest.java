package com.ppinol.coworkingapp.core.ui;

import com.ppinol.coworkingapp.core.domain.office.Office;
import com.ppinol.coworkingapp.core.domain.office.OfficeNumber;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryOfficeRepository;
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
class RegisterOfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryOfficeRepository repository;

    @BeforeEach
    void setUp() {
        repository.clear();
    }

    @Test
    void testRegisterOfficeSuccess() throws Exception {
        String json = "{\"number\":\"101\",\"leasePeriod\":24,\"status\":\"Active\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Office office = repository.findByNumber(new OfficeNumber(101));
        assertThat(office).isNotNull();
        assertThat(office.getId()).isNotNull();
        assertThat(office.getCreatedAt()).isNotNull();
        assertThat(office.getUpdatedAt()).isNotNull();
        assertThat(office.getStatus().value().isActive()).isTrue();
        assertThat(office.getLeasePeriod().value()).isEqualTo(24);
    }

    @Test
    void testRegisterOfficeMissingLeasePeriod() throws Exception {
        String json = "{\"number\":\"102\",\"status\":\"Inactive\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Office office = repository.findByNumber(new OfficeNumber(102));
        assertThat(office).isNotNull();
        assertThat(office.getLeasePeriod().value()).isEqualTo(12);
        assertThat(office.getStatus().value().isActive()).isFalse();
    }

    @Test
    void testRegisterOfficeMissingStatus() throws Exception {
        String json = "{\"number\":\"103\",\"leasePeriod\":18}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Office office = repository.findByNumber(new OfficeNumber(103));
        assertThat(office).isNotNull();
        assertThat(office.getLeasePeriod().value()).isEqualTo(18);
        assertThat(office.getStatus().value().isActive()).isTrue();
    }

    @Test
    void testRegisterOfficeInvalidInput() throws Exception {
        String json = "{\"number\":\"   \",\"leasePeriod\":24,\"status\":\"Active\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterOfficeInvalidLeasePeriod() throws Exception {
        String json = "{\"number\":\"104\",\"leasePeriod\":0,\"status\":\"Active\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterOfficeInvalidStatus() throws Exception {
        String json = "{\"number\":\"105\",\"leasePeriod\":24,\"status\":\"Unknown\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterOfficeDuplicate() throws Exception {
        String json = "{\"number\":\"101\",\"leasePeriod\":24,\"status\":\"Active\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(498));
    }
}
