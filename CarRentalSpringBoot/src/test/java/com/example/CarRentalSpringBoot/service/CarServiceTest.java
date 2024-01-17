package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.CarRental.entity.Car;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.CarDto;
import com.example.CarRentalSpringBoot.CarRental.repository.CarRepository;
import com.example.CarRentalSpringBoot.CarRental.service.impl.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;


    private CarDto carDto;
    private Car car;

    @BeforeEach
    void setUp() {
        carDto = new CarDto();
        car = new Car();
    }

    @Test
    void addCar() {
        carService.addCar(carDto);
        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void getCarByIdFound() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(car));
        CarDto result = carService.getCarById(1L);
        assertNotNull(result);
    }

    @Test
    void getCarByIdNotFound() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.empty());
        CarDto result = carService.getCarById(1L);
        assertNull(result);
    }

    @Test
    void getAllCars() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(car));
        List<CarDto> result = carService.getAllCars();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void getAllCarsEmptyList() {
        when(carRepository.findAll()).thenReturn(Arrays.asList());
        List<CarDto> result = carService.getAllCars();
        assertNull(result);
    }
}

