package com.example.CarRentalSpringBoot.CarRental.service;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    List<ReservationDto> getAllReservations();

    void addReservation(ReservationDto reservation);

    List<ReservationDto> getAllReservationsByCarId(Long carId);

    List<ReservationDto> getAllReservationsByUserId(Long userId);
}
