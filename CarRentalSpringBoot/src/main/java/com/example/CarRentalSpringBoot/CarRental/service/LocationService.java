package com.example.CarRentalSpringBoot.CarRental.service;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.LocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllLocations();

    void newLocation(LocationDto locationDto);

    LocationDto getLocation(Long locationId);

    void deleteLocation(Long locationId);

}
