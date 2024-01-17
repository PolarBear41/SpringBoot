package com.example.CarRentalSpringBoot.CarRental.service.impl;

import com.example.CarRentalSpringBoot.CarRental.entity.Invoice;
import com.example.CarRentalSpringBoot.CarRental.entity.Payment;
import com.example.CarRentalSpringBoot.CarRental.entity.User;
import com.example.CarRentalSpringBoot.CarRental.pojo.converter.PaymentConverter;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.PaymentDto;
import com.example.CarRentalSpringBoot.CarRental.repository.InvoiceRepository;
import com.example.CarRentalSpringBoot.CarRental.repository.PaymentRepository;
import com.example.CarRentalSpringBoot.CarRental.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void makePayment(PaymentDto paymentDto) {
        Invoice invoice = invoiceRepository.findByInvoiceId(paymentDto.getInvoiceId());
        if(invoice == null) {
            throw new RuntimeException("Invoice not found");
        }

        paymentRepository.save(PaymentConverter.dtoConvertToPayment(paymentDto, invoice));
    }

    @Override
    public PaymentDto getPaymentBasedOnId(Long paymentId) {
        Payment payment = paymentRepository.findByPaymentId(paymentId);
        if(payment == null) {
            throw new RuntimeException("No payment found based on paymentId");
        }
        return PaymentConverter.paymentConvertToDto(payment);
    }


    @Override
    public List<PaymentDto> getAllPayments() {
        var payments = paymentRepository.findAll();
        if(payments.isEmpty()) {
            throw new RuntimeException("No payments found");
        }
        return payments.stream()
                .map(PaymentConverter::paymentConvertToDto)
                .toList();
    }
}
