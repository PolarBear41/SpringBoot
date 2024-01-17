package com.example.CarRentalSpringBoot.CarRental.pojo.converter;

import com.example.CarRentalSpringBoot.CarRental.entity.Location;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.LocationDto;

public class LocationConverter {
    public static LocationDto locationConvertToDto(Location location) {
        return LocationDto.builder()
                .name(location.getName())
                .address(location.getAddress())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .city(location.getCity())
                .state(location.getState())
                .country(location.getCountry())
                .build();
    }

    public static Location dtoConvertToLocation(LocationDto locationDto) {
        return Location.builder()
                .name(locationDto.getName())
                .address(locationDto.getAddress())
                .latitude(locationDto.getLatitude())
                .longitude(locationDto.getLongitude())
                .city(locationDto.getCity())
                .state(locationDto.getState())
                .country(locationDto.getCountry())
                .build();
    }
}
