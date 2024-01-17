package com.example.CarRentalSpringBoot.CarRental.controller;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.PaymentDto;
import com.example.CarRentalSpringBoot.CarRental.service.impl.PaymentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentServiceImpl paymentService;

    @PostMapping
    @Operation(summary = "Make Payment", description = "Make a payment.")
    public ResponseEntity<Void> makePayment(@RequestBody @Valid PaymentDto paymentDto) {
        paymentService.makePayment(paymentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{paymentId}")
    @Operation(summary = "Get Payment by ID", description = "Retrieve payment details by providing its unique ID.")
    public PaymentDto getPayment(@PathVariable Long paymentId) {
        return paymentService.getPaymentBasedOnId(paymentId);
    }

    @GetMapping
    @Operation(summary = "Get All Payments", description = "Retrieve a list of all payments.")
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
