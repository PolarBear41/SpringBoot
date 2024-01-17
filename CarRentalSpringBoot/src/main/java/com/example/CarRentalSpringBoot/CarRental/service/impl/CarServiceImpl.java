package com.example.CarRentalSpringBoot.CarRental.service.impl;

import com.example.CarRentalSpringBoot.CarRental.entity.Car;
import com.example.CarRentalSpringBoot.CarRental.pojo.converter.CarConverter;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.CarRental.repository.CarRepository;
import com.example.CarRentalSpringBoot.CarRental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public void addCar(CarDto carDto) {
        carRepository.save(CarConverter.dtoConvertToCar(carDto));
    }

    @Override
    public void updateCar(Long carId, CarDto carDto) {
        Car car = carRepository.findByCarId(carId);
        if(car == null) {
            throw new RuntimeException("Car not found");
        }
        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        car.setRentalRate(carDto.getRentalRate());
        car.setLicensePlate(carDto.getLicensePlate());
        carRepository.save(car);
    }

    @Override
    public CarDto getCarById(Long carId) {
        var carOptional = carRepository.findById(carId);
        return carOptional.map(CarConverter::carConvertToDto).orElse(null);
    }

    @Override
    public List<CarDto> getAllCars() {
        var carsOptional = carRepository.findAll();
        if(carsOptional.isEmpty()) {
            return null;
        }
        return carsOptional.stream().map(CarConverter::carConvertToDto).collect(Collectors.toList());
    }
}
