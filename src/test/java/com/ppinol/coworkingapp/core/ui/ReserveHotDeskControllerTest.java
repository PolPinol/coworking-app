package com.ppinol.coworkingapp.core.ui;

import com.ppinol.coworkingapp.core.domain.UserId;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservation;
import com.ppinol.coworkingapp.core.domain.reservation.hotdesk.HotDeskReservationDate;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryHotDeskRepository;
import com.ppinol.coworkingapp.core.infrastructure.InMemoryHotDeskReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Execution(ExecutionMode.SAME_THREAD)
@SpringBootTest
@AutoConfigureMockMvc
class ReserveHotDeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InMemoryHotDeskReservationRepository repository;

    @Autowired
    private InMemoryHotDeskRepository hotDeskRepository;

    @BeforeEach
    void setUp() {
        hotDeskRepository.clear();
        repository.clear();
    }

    private void registerDefaultHotDesk(int number) throws Exception {
        String hotDeskJson = "{\"number\":" + number + "}";
        mockMvc.perform(post("/registerHotDesk")
                        .content(hotDeskJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testReserveHotDeskSuccess() throws Exception {
        registerDefaultHotDesk(1);

        String json = "{\"userId\":\"123e4567-e89b-12d3-a456-426614174010\", \"date\":\"2025-03-01\"}";
        mockMvc.perform(post("/reserveHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        HotDeskReservationDate date = new HotDeskReservationDate("2025-03-01");
        UserId id = new UserId("123e4567-e89b-12d3-a456-426614174010");
        HotDeskReservation reservation = this.repository.find(id, date);
        assertThat(reservation).isNotNull();
        assertThat(reservation.getReservationId()).isNotNull();
        assertThat(reservation.getCreatedAt()).isNotNull();
        assertThat(reservation.getUpdatedAt()).isNotNull();
        assertThat(reservation.getStatus().value().isActive()).isTrue();
    }

    @Test
    void testReserveHotDeskDuplicateReservation() throws Exception {
        registerDefaultHotDesk(2);
        registerDefaultHotDesk(3);

        String json = "{\"userId\":\"123e4567-e89b-12d3-a456-426614174012\", \"date\":\"2025-03-03\"}";

        mockMvc.perform(post("/reserveHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(post("/reserveHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    void testReserveHotDeskInvalidInputMissingUserId() throws Exception {
        String json = "{\"date\":\"2025-03-04\"}";
        mockMvc.perform(post("/reserveHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReserveHotDeskInvalidInputMissingDate() throws Exception {
        String json = "{\"userId\":\"123e4567-e89b-12d3-a456-426614174013\"}";
        mockMvc.perform(post("/reserveHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testReserveHotDeskInvalidInputWrongDateFormat() throws Exception {
        String json = "{\"userId\":\"123e4567-e89b-12d3-a456-426614174014\", \"date\":\"03-01-2025\"}";
        mockMvc.perform(post("/reserveHotDesk")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
