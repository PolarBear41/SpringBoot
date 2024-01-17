package com.example.CarRentalSpringBoot.controller;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.InvoiceDto;
import com.example.CarRentalSpringBoot.CarRental.service.InvoiceService;
import com.example.CarRentalSpringBoot.CarRental.controller.InvoiceController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
class InvoiceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    void getAllInvoices() throws Exception {
        when(invoiceService.getAllInvoices()).thenReturn(Collections.singletonList(new InvoiceDto()));

        mockMvc.perform(get("/invoice"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getAllInvoicesByUserId() throws Exception {
        Long userId = 1L;
        when(invoiceService.getAllInvoicesByUserId(userId)).thenReturn(Collections.singletonList(new InvoiceDto()));

        mockMvc.perform(get("/invoice/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }
}

