package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.CarRental.entity.Car;
import com.example.CarRentalSpringBoot.CarRental.entity.Feedback;
import com.example.CarRentalSpringBoot.CarRental.entity.User;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.CarRental.repository.CarRepository;
import com.example.CarRentalSpringBoot.CarRental.repository.FeedbackRepository;
import com.example.CarRentalSpringBoot.CarRental.repository.UserRepository;
import com.example.CarRentalSpringBoot.CarRental.service.impl.FeedbackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

public class FeedbackServiceTest {

    @InjectMocks
    private FeedbackServiceImpl feedbackService;

    @Mock
    private FeedbackRepository feedbackRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFeedbacks() {
        List<Feedback> feedbacks = createFeedbackList();
        Mockito.when(feedbackRepository.findAll()).thenReturn(feedbacks);

        List<FeedbackDto> result = feedbackService.getAllFeedbacks();

        assertNotNull(result);
        assertEquals(feedbacks.size(), result.size());
    }

    @Test
    void testLeaveFeedback() {
        FeedbackDto feedbackDto = createFeedbackDto();
        User user = createUser();
        Car car = createCar();
        Mockito.when(userRepository.findByUserId(anyLong())).thenReturn(user);
        Mockito.when(carRepository.findByCarId(anyLong())).thenReturn(car);
        Mockito.when(feedbackRepository.save(any(Feedback.class))).thenReturn(createFeedback());

        assertDoesNotThrow(() -> feedbackService.leaveFeedback(feedbackDto));
    }

    @Test
    void testLeaveFeedbackUserNotFound() {
        FeedbackDto feedbackDto = createFeedbackDto();
        Mockito.when(userRepository.findByUserId(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> feedbackService.leaveFeedback(feedbackDto));
    }

    @Test
    void testLeaveFeedbackCarNotFound() {
        FeedbackDto feedbackDto = createFeedbackDto();
        User user = createUser();
        Mockito.when(userRepository.findByUserId(anyLong())).thenReturn(user);
        Mockito.when(carRepository.findByCarId(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> feedbackService.leaveFeedback(feedbackDto));
    }

    @Test
    void testGetAllFeedbacksByCarId() {
        List<Feedback> feedbacks = createFeedbackList();
        Mockito.when(feedbackRepository.findByCarId(anyLong())).thenReturn(feedbacks);

        List<FeedbackDto> result = feedbackService.getAllFeedbacksByCarId(1L);

        assertNotNull(result);
        assertEquals(feedbacks.size(), result.size());
    }

    @Test
    void testGetAllFeedbacksByUserId() {
        List<Feedback> feedbacks = createFeedbackList();
        Mockito.when(feedbackRepository.findByUserId(anyLong())).thenReturn(feedbacks);

        List<FeedbackDto> result = feedbackService.getAllFeedbacksByUserId(1L);

        assertNotNull(result);
        assertEquals(feedbacks.size(), result.size());
    }

    @Test
    void testDeleteFeedback() {
        Mockito.when(feedbackRepository.existsById(anyLong())).thenReturn(true);

        boolean result = feedbackService.deleteFeedback(1L);

        assertTrue(result);
        Mockito.verify(feedbackRepository).deleteById(1L);
    }

    @Test
    void testDeleteFeedbackNotFound() {
        Mockito.when(feedbackRepository.existsById(anyLong())).thenReturn(false);

        boolean result = feedbackService.deleteFeedback(1L);

        assertFalse(result);
        Mockito.verify(feedbackRepository, Mockito.never()).deleteById(anyLong());
    }

    private FeedbackDto createFeedbackDto() {
        FeedbackDto feedbackDto = new FeedbackDto();
        feedbackDto.setUserId(1L);
        feedbackDto.setCarId(1L);
        return feedbackDto;
    }

    private User createUser() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("username");
        return user;
    }

    private Car createCar() {
        Car car = new Car();
        car.setCarId(1L);
        car.setMake("make");
        return car;
    }

    private Feedback createFeedback() {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(1L);
        feedback.setCar(createCar());
        feedback.setUser(createUser());
        return feedback;
    }

    private List<Feedback> createFeedbackList() {
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(createFeedback());
        feedbacks.add(createFeedback());
        return feedbacks;
    }
}
