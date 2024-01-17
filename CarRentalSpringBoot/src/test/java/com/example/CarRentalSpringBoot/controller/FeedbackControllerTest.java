package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.CarRental.controller.FeedbackController;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.CarRental.service.impl.FeedbackServiceImpl;
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
class FeedbackControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FeedbackServiceImpl feedbackService;

    @InjectMocks
    private FeedbackController feedbackController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
    }

    @Test
    void leaveFeedback() throws Exception {
        FeedbackDto feedbackDto = new FeedbackDto(1L,"Good car", new Date(), 5, 1L, 1L);

        mockMvc.perform(post("/feedback")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(feedbackDto)))
                .andExpect(status().isCreated());

        verify(feedbackService, times(1)).leaveFeedback(any(FeedbackDto.class));
    }

    @Test
    void getAllFeedbacks() throws Exception {
        when(feedbackService.getAllFeedbacks()).thenReturn(Collections.singletonList(new FeedbackDto()));

        mockMvc.perform(get("/feedback"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getFeedbacksByCarId() throws Exception {
        Long carId = 1L;
        when(feedbackService.getAllFeedbacksByCarId(carId)).thenReturn(Collections.singletonList(new FeedbackDto()));

        mockMvc.perform(get("/feedback/{carId}", carId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getFeedbacksByUserId() throws Exception {
        Long userId = 1L;
        when(feedbackService.getAllFeedbacksByUserId(userId)).thenReturn(Collections.singletonList(new FeedbackDto()));

        mockMvc.perform(get("/feedback/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void deleteFeedback() throws Exception {
        Long feedbackId = 1L;

        when(feedbackService.deleteFeedback(feedbackId)).thenReturn(true);

        mockMvc.perform(delete("/feedback/{feedbackId}", feedbackId))
                .andExpect(status().isOk());
    }
}
