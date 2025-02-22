package com.ppinol.coworkingapp.core.ui.office;

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
        String json = "{\"number\":\"101\",\"leasePeriod\":\"24\",\"status\":\"Active\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Office office = repository.findByNumber(new OfficeNumber("101"));
        assertThat(office).isNotNull();
    }

    @Test
    void testRegisterOfficeInvalidInput() throws Exception {
        // Invalid input: empty number field
        String json = "{\"number\":\"   \",\"leasePeriod\":\"24\",\"status\":\"Active\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterOfficeDuplicate() throws Exception {
        String json = "{\"number\":\"101\",\"leasePeriod\":\"24\",\"status\":\"Active\"}";
        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/registerOffice")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
