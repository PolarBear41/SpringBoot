package com.example.CarRentalSpringBoot.CarRental.service;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.CarDto;

import java.util.List;


public interface CarService {
    void addCar(CarDto carDto);
    CarDto getCarById(Long carId);
    List<CarDto> getAllCars();
    void updateCar(Long carId, CarDto carDto);
}

