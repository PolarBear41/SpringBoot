package com.example.CarRentalSpringBoot.CarRental.controller;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.FeedbackDto;
import com.example.CarRentalSpringBoot.CarRental.service.impl.FeedbackServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/feedback")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackServiceImpl feedbackService;

    @PostMapping
    @Operation(summary = "Leave feedback", description = "Leave feedback for a car rental.")
    public ResponseEntity<Void> leaveFeedback(@RequestBody @Valid FeedbackDto feedbackDto) {
        feedbackService.leaveFeedback(feedbackDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all feedbacks", description = "Get a list of all feedbacks.")
    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{carId}")
    @Operation(summary = "Get feedbacks by car ID", description = "Get feedbacks for a specific car by its ID.")
    public List<FeedbackDto> getFeedbacksByCarId(@PathVariable Long carId) {
        return feedbackService.getAllFeedbacksByCarId(carId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get feedbacks by user ID", description = "Get feedbacks submitted by a specific user.")
    public List<FeedbackDto> getFeedbacksByUserId(@PathVariable Long userId) {
        return feedbackService.getAllFeedbacksByUserId(userId);
    }

    @DeleteMapping("/{feedbackId}")
    @Operation(summary = "Delete Feedback by ID", description = "Delete feedback by providing its unique ID.")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long feedbackId) {
        boolean deleted = feedbackService.deleteFeedback(feedbackId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


