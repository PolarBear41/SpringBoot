package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.CarRental.entity.Invoice;
import com.example.CarRentalSpringBoot.CarRental.entity.Payment;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.PaymentDto;
import com.example.CarRentalSpringBoot.CarRental.repository.InvoiceRepository;
import com.example.CarRentalSpringBoot.CarRental.repository.PaymentRepository;
import com.example.CarRentalSpringBoot.CarRental.service.impl.PaymentServiceImpl;
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

public class PaymentServiceTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakePayment() {
        PaymentDto paymentDto = createPaymentDto();
        Invoice invoice = createInvoice();
        Mockito.when(invoiceRepository.findByInvoiceId(anyLong())).thenReturn(invoice);
        Mockito.when(paymentRepository.save(any(Payment.class))).thenReturn(createPayment());

        assertDoesNotThrow(() -> paymentService.makePayment(paymentDto));
    }

    @Test
    void testMakePaymentInvoiceNotFound() {
        PaymentDto paymentDto = createPaymentDto();
        Mockito.when(invoiceRepository.findByInvoiceId(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> paymentService.makePayment(paymentDto));
    }

    @Test
    void testGetPaymentBasedOnId() {
        Payment payment = createPayment();
        Mockito.when(paymentRepository.findByPaymentId(anyLong())).thenReturn(payment);

        PaymentDto result = paymentService.getPaymentBasedOnId(1L);

        assertNotNull(result);
        assertEquals(payment.getPaymentMethod(), result.getPaymentMethod());
    }

    @Test
    void testGetPaymentBasedOnIdNotFound() {
        Mockito.when(paymentRepository.findByPaymentId(anyLong())).thenReturn(null);

        assertThrows(RuntimeException.class, () -> paymentService.getPaymentBasedOnId(1L));
    }

    @Test
    void testGetAllPayments() {
        List<Payment> payments = createPaymentList();
        Mockito.when(paymentRepository.findAll()).thenReturn(payments);

        List<PaymentDto> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(payments.size(), result.size());
    }

    @Test
    void testGetAllPaymentsEmptyList() {
        List<Payment> payments = new ArrayList<>();
        Mockito.when(paymentRepository.findAll()).thenReturn(payments);

        assertThrows(RuntimeException.class, () -> paymentService.getAllPayments());
    }

    private PaymentDto createPaymentDto() {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setInvoiceId(1L);
        // Set other payment properties
        return paymentDto;
    }

    private Invoice createInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1L);
        // Set other invoice properties
        return invoice;
    }

    private Payment createPayment() {
        Payment payment = new Payment();
        payment.setPaymentId(1L);
        payment.setPaymentMethod("Cash");
        payment.setInvoice(createInvoice());
        return payment;
    }

    private List<Payment> createPaymentList() {
        List<Payment> payments = new ArrayList<>();
        payments.add(createPayment());
        payments.add(createPayment());
        return payments;
    }
}
