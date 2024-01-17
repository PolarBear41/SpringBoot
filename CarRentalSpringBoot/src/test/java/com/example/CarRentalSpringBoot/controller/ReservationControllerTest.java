package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.ReservationDto;
import com.example.CarRentalSpringBoot.CarRental.service.ReservationService;
import com.example.CarRentalSpringBoot.CarRental.controller.ReservationController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class ReservationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
    }

    @Test
    void createReservation() throws Exception {
        ReservationDto reservationDto = new ReservationDto(Date.from(java.time.Instant.now()), "Pending",1L, 1L);
        mockMvc.perform(post("/reservation")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reservationDto)))
                .andExpect(status().isCreated());

        verify(reservationService, times(1)).addReservation(any(ReservationDto.class));
    }

    @Test
    void getAllReservations() throws Exception {
        when(reservationService.getAllReservations()).thenReturn(Collections.singletonList(new ReservationDto()));

        mockMvc.perform(get("/reservation"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getAllReservationsByCarId() throws Exception {
        Long carId = 1L;
        when(reservationService.getAllReservationsByCarId(carId)).thenReturn(Collections.singletonList(new ReservationDto()));

        mockMvc.perform(get("/reservation/{carId}", carId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getAllReservationsByUserId() throws Exception {
        Long userId = 1L;
        when(reservationService.getAllReservationsByUserId(userId)).thenReturn(Collections.singletonList(new ReservationDto()));

        mockMvc.perform(get("/reservation/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}

