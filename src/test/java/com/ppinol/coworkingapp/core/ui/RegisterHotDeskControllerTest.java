package com.ppinol.coworkingapp.core.ui;

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
    void setUp() {
        repository.clear();
    }

    @Test
    void testRegisterHotDeskSuccess() throws Exception {
        String json = "{\"number\":1}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        HotDesk hotDesk = repository.findByNumber(new HotDeskNumber(1));
        assertThat(hotDesk).isNotNull();
        assertThat(hotDesk.getId()).isNotNull();
        assertThat(hotDesk.getCreatedAt()).isNotNull();
        assertThat(hotDesk.getUpdatedAt()).isNotNull();
        assertThat(hotDesk.getStatus().value().isActive()).isTrue();
    }

    @Test
    void testRegisterHotDeskInvalidInput() throws Exception {
        String json = "{\"number\":0}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterHotDeskMissingNumber() throws Exception {
        String json = "{}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterHotDeskNullNumber() throws Exception {
        String json = "{\"number\":null}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterHotDeskNonInteger() throws Exception {
        String json = "{\"number\":\"abc\"}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterHotDeskDuplicate() throws Exception {
        String json = "{\"number\":1}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/registerHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(498));
    }
}
