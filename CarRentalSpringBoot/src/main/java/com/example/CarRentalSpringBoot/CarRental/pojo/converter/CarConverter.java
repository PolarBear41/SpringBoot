package com.example.CarRentalSpringBoot.CarRental.pojo.converter;

import com.example.CarRentalSpringBoot.CarRental.entity.Car;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.CarDto;

public class CarConverter {
    public static CarDto carConvertToDto(Car car) {
        return CarDto.builder()
                .make(car.getMake())
                .model(car.getModel())
                .year(car.getYear())
                .rentalRate(car.getRentalRate())
                .licensePlate(car.getLicensePlate())
                .build();
    }
    public static Car dtoConvertToCar(CarDto carDto) {
        return Car.builder()
                .make(carDto.getMake())
                .model(carDto.getModel())
                .year(carDto.getYear())
                .rentalRate(carDto.getRentalRate())
                .licensePlate(carDto.getLicensePlate())
                .build();
    }
}
