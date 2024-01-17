package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.CarRental.entity.Location;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.LocationDto;
import com.example.CarRentalSpringBoot.CarRental.repository.LocationRepository;
import com.example.CarRentalSpringBoot.CarRental.service.impl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationServiceImpl locationService;

    private Location location;
    private LocationDto locationDto;

    @BeforeEach
    void setUp() {
        location = new Location();
        locationDto = new LocationDto();
    }

    @Test
    void getAllLocations() {
        when(locationRepository.findAll()).thenReturn(Collections.singletonList(location));
        List<LocationDto> result = locationService.getAllLocations();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void newLocation() {
        locationService.newLocation(locationDto);
        verify(locationRepository, times(1)).save(any(Location.class));
    }

    @Test
    void getLocation() {
        Long locationId = 1L;
        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));
        LocationDto result = locationService.getLocation(locationId);

        assertNotNull(result);
    }
}

