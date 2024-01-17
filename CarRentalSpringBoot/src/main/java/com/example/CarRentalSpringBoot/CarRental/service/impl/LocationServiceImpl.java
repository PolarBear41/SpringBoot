package com.example.CarRentalSpringBoot.CarRental.service.impl;

import com.example.CarRentalSpringBoot.CarRental.pojo.converter.LocationConverter;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.LocationDto;
import com.example.CarRentalSpringBoot.CarRental.repository.LocationRepository;
import com.example.CarRentalSpringBoot.CarRental.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<LocationDto> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(LocationConverter::locationConvertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void newLocation(LocationDto locationDto) {
        locationRepository.save(LocationConverter.dtoConvertToLocation(locationDto));
    }

    @Override
    public LocationDto getLocation(Long locationId) {
        var locationOptional = locationRepository.findById(locationId);
        return locationOptional.map(LocationConverter::locationConvertToDto).orElse(null);
    }

    @Override
    public void deleteLocation(Long locationId) {
        if (locationRepository.existsById(locationId)) {
            locationRepository.deleteById(locationId);
        }
    }
}
