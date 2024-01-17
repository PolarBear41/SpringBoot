package com.example.CarRentalSpringBoot.CarRental.service.impl;

import com.example.CarRentalSpringBoot.CarRental.entity.Car;
import com.example.CarRentalSpringBoot.CarRental.entity.User;
import com.example.CarRentalSpringBoot.CarRental.pojo.converter.ReservationConverter;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.ReservationDto;
import com.example.CarRentalSpringBoot.CarRental.repository.CarRepository;
import com.example.CarRentalSpringBoot.CarRental.repository.ReservationRepository;
import com.example.CarRentalSpringBoot.CarRental.repository.UserRepository;
import com.example.CarRentalSpringBoot.CarRental.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public List<ReservationDto> getAllReservations() {
        var reservations = reservationRepository.findAll();
        if(reservations.isEmpty()) {
            throw new RuntimeException("No reservations found");
        }
        return reservations.stream()
                .map(ReservationConverter::reservationConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void addReservation(ReservationDto reservation) {
        User user = userRepository.findByUserId(reservation.getUserId());
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        Car car = carRepository.findByCarId(reservation.getCarId());
        if(car == null) {
            throw new RuntimeException("Car not found");
        }
        reservationRepository.save(ReservationConverter.dtoConvertToReservation(reservation, user, car));
    }

    @Override
    public List<ReservationDto> getAllReservationsByCarId(Long carId) {
        var reservations = reservationRepository.findByCarId(carId);
        if(reservations.isEmpty()) {
            throw new RuntimeException("No reservations found based on CarId");
        }
        return reservations.stream()
                .map(ReservationConverter::reservationConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> getAllReservationsByUserId(Long userId) {
        var reservations = reservationRepository.findByUserId(userId);
        if(reservations.isEmpty()) {
            throw new RuntimeException("No reservations found based on UserId");
        }
        return reservations.stream()
                .map(ReservationConverter::reservationConvertToDto)
                .collect(Collectors.toList());
    }
}
