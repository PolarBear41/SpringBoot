package com.example.CarRentalSpringBoot.CarRental.service.impl;

import com.example.CarRentalSpringBoot.CarRental.pojo.converter.InvoiceConverter;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.InvoiceDto;
import com.example.CarRentalSpringBoot.CarRental.repository.InvoiceRepository;
import com.example.CarRentalSpringBoot.CarRental.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDto> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(InvoiceConverter::invoiceConvertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<InvoiceDto> getAllInvoicesByUserId(Long userId) {

        return invoiceRepository.findByUserId(userId).stream()
                .map(InvoiceConverter::invoiceConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void createInvoice(InvoiceDto invoiceDto) {
        invoiceRepository.save(InvoiceConverter.dtoConvertToInvoice(invoiceDto));
    }


}
