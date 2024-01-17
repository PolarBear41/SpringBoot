package com.example.CarRentalSpringBoot.CarRental.pojo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackDto {
    private Long feedbackId;

    @NotNull(message = "Feedback text is required")
    @Size(max = 500, message = "Feedback text cannot be longer than 500 characters")
    private String feedbackText;

    @NotNull(message = "Feedback date is required")
    @PastOrPresent(message = "Feedback date must be in the past or present")
    private Date feedbackDate;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Car ID is required")
    private Long carId;

}

