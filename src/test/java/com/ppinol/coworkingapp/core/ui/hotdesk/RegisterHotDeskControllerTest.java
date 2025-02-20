package com.ppinol.coworkingapp.core.ui.hotdesk;

import com.ppinol.coworkingapp.core.domain.hotdesk.HotDesk;
import com.ppinol.coworkingapp.core.domain.hotdesk.HotDeskNumber;
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
class RegisterHotDeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryHotDeskRepository repository;

    @BeforeEach
    void clearRepository() {
        repository.clear();
    }

    @Test
    void testRegisterHotDeskSuccess() throws Exception {
        mockMvc.perform(post("/registerHotDesk")
                        .content("{\"number\":\"5\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        HotDeskNumber number = new HotDeskNumber("5");
        HotDesk storedHotDesk = repository.findByNumber(number);
        assertThat(storedHotDesk).isNotNull();
        assertThat(storedHotDesk.getNumber().toString()).isEqualTo(number.toString());
    }

    @Test
    void testRegisterHotDeskInvalidNumber() throws Exception {
        mockMvc.perform(post("/registerHotDesk")
                        .content("invalid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterHotDeskDuplicate() throws Exception {
        mockMvc.perform(post("/registerHotDesk")
                        .content("{\"number\":\"10\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/registerHotDesk")
                        .content("{\"number\":\"10\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
