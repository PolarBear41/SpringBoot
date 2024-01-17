package com.example.CarRentalSpringBoot.CarRental.service;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.PaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    void makePayment(PaymentDto paymentDto);
    PaymentDto getPaymentBasedOnId(Long paymentId);
    List<PaymentDto> getAllPayments();
}
