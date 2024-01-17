package com.example.CarRentalSpringBoot.CarRental.pojo.converter;

import com.example.CarRentalSpringBoot.CarRental.entity.Car;
import com.example.CarRentalSpringBoot.CarRental.entity.Feedback;
import com.example.CarRentalSpringBoot.CarRental.entity.User;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.FeedbackDto;


public class FeedbackConverter {

    public static FeedbackDto feedbackConvertToDto(Feedback feedback) {
        return FeedbackDto.builder()
                .feedbackId(feedback.getFeedbackId())
                .feedbackText(feedback.getFeedbackText())
                .feedbackDate(feedback.getFeedbackDate())
                .rating(feedback.getRating())
                .userId(feedback.getUser().getUserId())
                .carId(feedback.getCar().getCarId())
                .build();
    }

    public static Feedback dtoConvertToFeedback(FeedbackDto feedbackDto, User user, Car car) {
        return Feedback.builder()
                .feedbackId(feedbackDto.getFeedbackId())
                .feedbackText(feedbackDto.getFeedbackText())
                .feedbackDate(feedbackDto.getFeedbackDate())
                .rating(feedbackDto.getRating())
                .car(car)
                .user(user)
                .build();
    }
}
