package com.example.CarRentalSpringBoot.CarRental.controller;


import com.example.CarRentalSpringBoot.CarRental.pojo.dto.InvoiceDto;
import com.example.CarRentalSpringBoot.CarRental.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    @Operation(summary = "Get All Invoices", description = "Retrieve a list of all invoices.")
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get Invoices by User ID", description = "Retrieve invoices for a specific user by their ID.")
    public List<InvoiceDto> getAllInvoicesByUserId(@PathVariable Long userId) {
        return invoiceService.getAllInvoicesByUserId(userId);
    }
}
