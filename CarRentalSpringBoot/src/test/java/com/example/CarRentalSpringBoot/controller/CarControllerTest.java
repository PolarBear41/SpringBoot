package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.CarRental.controller.CarController;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.CarRental.service.impl.CarServiceImpl;
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
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class CarControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarServiceImpl carService;

    @InjectMocks
    private CarController carController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createCar() throws Exception {
        CarDto carDto = new CarDto("Toyota", "Camry", 2015, new BigDecimal("50.00"), "ABC123");

        mockMvc.perform(post("/car")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDto)))
                .andExpect(status().isCreated());

        verify(carService, times(1)).addCar(any(CarDto.class));
    }

    @Test
    void getCar() throws Exception {
        Long carId = 1L;
        CarDto carDto = new CarDto();


        when(carService.getCarById(carId)).thenReturn(carDto);

        mockMvc.perform(get("/car/{carId}", carId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty());
    }


    @Test
    void getAllCars() throws Exception {
        List<CarDto> carDtos = Collections.singletonList(new CarDto());

        when(carService.getAllCars()).thenReturn(carDtos);

        mockMvc.perform(get("/car"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").isNotEmpty());
    }
    @Test
    void updateCar() throws Exception {
        Long carId = 1L;
        CarDto carDto = new CarDto("Toyota", "Camry", 2015, new BigDecimal("50.00"), "ABC123");

        mockMvc.perform(put("/car/{carId}", carId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(carDto)))
                .andExpect(status().isOk());

        verify(carService, times(1)).updateCar(eq(carId), any(CarDto.class));
    }

}

