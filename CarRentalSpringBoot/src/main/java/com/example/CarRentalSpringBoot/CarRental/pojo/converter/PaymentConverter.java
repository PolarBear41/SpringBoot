package com.example.CarRentalSpringBoot.CarRental.pojo.converter;

import com.example.CarRentalSpringBoot.CarRental.entity.Invoice;
import com.example.CarRentalSpringBoot.CarRental.entity.Payment;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.PaymentDto;



public class PaymentConverter {
    public static PaymentDto paymentConvertToDto(Payment payment) {
        return PaymentDto.builder()
                .paymentDate(payment.getPaymentDate())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .invoiceId(payment.getInvoice().getInvoiceId())
                .build();
    }
    public static Payment dtoConvertToPayment(PaymentDto paymentDto, Invoice invoice) {
        return Payment.builder()
                .paymentDate(paymentDto.getPaymentDate())
                .amount(paymentDto.getAmount())
                .paymentMethod(paymentDto.getPaymentMethod())
                .invoice(invoice)
                .build();
    }
}
