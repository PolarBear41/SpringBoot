package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.CarRental.entity.Invoice;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.InvoiceDto;
import com.example.CarRentalSpringBoot.CarRental.repository.InvoiceRepository;
import com.example.CarRentalSpringBoot.CarRental.service.impl.InvoiceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    private Invoice invoice;
    private InvoiceDto invoiceDto;

    @BeforeEach
    void setUp() {
        invoice = new Invoice();
        invoiceDto = new InvoiceDto();
    }

    @Test
    void getAllInvoices() {
        when(invoiceRepository.findAll()).thenReturn(Collections.singletonList(invoice));
        List<InvoiceDto> result = invoiceService.getAllInvoices();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

    }

    @Test
    void getAllInvoicesByUserId() {
        Long userId = 123L;
        when(invoiceRepository.findByUserId(userId)).thenReturn(Collections.singletonList(invoice));
        List<InvoiceDto> result = invoiceService.getAllInvoicesByUserId(userId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

    }

    @Test
    void createInvoice() {
        invoiceService.createInvoice(invoiceDto);

        verify(invoiceRepository, times(1)).save(any(Invoice.class));

    }
}
