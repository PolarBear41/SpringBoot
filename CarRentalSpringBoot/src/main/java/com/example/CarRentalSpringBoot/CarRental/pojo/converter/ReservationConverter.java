package com.example.CarRentalSpringBoot.CarRental.pojo.converter;

import com.example.CarRentalSpringBoot.CarRental.entity.Car;
import com.example.CarRentalSpringBoot.CarRental.entity.Reservation;
import com.example.CarRentalSpringBoot.CarRental.entity.User;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.ReservationDto;


public class ReservationConverter {
    public static ReservationDto reservationConvertToDto(Reservation reservation) {
        return ReservationDto.builder()
                .reservationDate(reservation.getReservationDate())
                .status(reservation.getStatus())
                .carId(reservation.getCar().getCarId())
                .userId(reservation.getUser().getUserId())
                .build();
    }

    public static Reservation dtoConvertToReservation(ReservationDto reservationDto, User user, Car car) {
        return Reservation.builder()
                .reservationDate(reservationDto.getReservationDate())
                .status(reservationDto.getStatus())
                .car(car)
                .user(user)
                .build();
    }
}
