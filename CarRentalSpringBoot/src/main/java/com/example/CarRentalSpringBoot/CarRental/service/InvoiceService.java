package com.example.CarRentalSpringBoot.CarRental.service;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.InvoiceDto;


import java.util.List;

public interface InvoiceService {

   List<InvoiceDto> getAllInvoices();

   List<InvoiceDto> getAllInvoicesByUserId(Long userId);

   void createInvoice(InvoiceDto invoiceDto);

}
