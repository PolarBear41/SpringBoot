package com.example.CarRentalSpringBoot.CarRental.service;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.FeedbackDto;


import java.util.List;


public interface FeedbackService {
    List<FeedbackDto> getAllFeedbacks();

    void leaveFeedback(FeedbackDto feedbackDto);

    List<FeedbackDto> getAllFeedbacksByCarId(Long carId);

    List<FeedbackDto> getAllFeedbacksByUserId(Long userId);

    boolean deleteFeedback(Long feedbackId);
}
