package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.CarRental.controller.UserController;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.UserDto;
import com.example.CarRentalSpringBoot.CarRental.service.UserService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void createUser() throws Exception {
        UserDto userDto = new UserDto("username", "password", "email@gmail.com", "address", "firstName", "lastName");

        mockMvc.perform(post("/user")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated());

        verify(userService, times(1)).createUser(any(UserDto.class));
    }

    @Test
    void updateUser() throws Exception {
        Long userId = 1L;
        UserDto userDto = new UserDto("username", "password", "email@gmail.com", "address", "firstName", "lastName");

        mockMvc.perform(put("/user/{userId}", userId)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk());

        verify(userService, times(1)).updateUser(eq(userId), any(UserDto.class));
    }

    @Test
    void getSpecificUser() throws Exception {
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(new UserDto());

        mockMvc.perform(get("/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(new UserDto()));

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}

