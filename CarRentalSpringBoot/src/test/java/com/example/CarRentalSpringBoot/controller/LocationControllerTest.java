package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.CarRental.controller.LocationController;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.LocationDto;
import com.example.CarRentalSpringBoot.CarRental.service.impl.LocationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class LocationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LocationServiceImpl locationService;

    @InjectMocks
    private LocationController locationController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(locationController).build();
    }

    @Test
    void newLocation() throws Exception {
        LocationDto locationDto = new LocationDto("Suceava", "Strada Stefan cel Mare", new BigDecimal("47.637520"), new BigDecimal("26.259280"), "Suceava", "Suceava", "Romania");
        mockMvc.perform(post("/location")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locationDto)))
                .andExpect(status().isCreated());

        verify(locationService, times(1)).newLocation(any(LocationDto.class));
    }

    @Test
    void getLocation() throws Exception {
        Long locationId = 1L;
        when(locationService.getLocation(locationId)).thenReturn(new LocationDto());

        mockMvc.perform(get("/location/{locationId}", locationId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty());
    }

    @Test
    void getAllLocations() throws Exception {
        when(locationService.getAllLocations()).thenReturn(Collections.singletonList(new LocationDto()));

        mockMvc.perform(get("/location"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}
