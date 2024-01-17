package com.example.CarRentalSpringBoot.CarRental.controller;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.ReservationDto;
import com.example.CarRentalSpringBoot.CarRental.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @Operation(summary = "Create Reservation", description = "Create a new reservation.")
    public ResponseEntity<Void> createReservation(@RequestBody @Valid ReservationDto reservationdto) {
        reservationService.addReservation(reservationdto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get All Reservations", description = "Retrieve a list of all reservations.")
    public List<ReservationDto> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{carId}")
    @Operation(summary = "Get Reservations by Car ID", description = "Retrieve reservations for a specific car by its ID.")
    public List<ReservationDto> getAllReservationsByCarId(@PathVariable Long carId) {
        return reservationService.getAllReservationsByCarId(carId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get Reservations by User ID", description = "Retrieve reservations made by a specific user.")
    public List<ReservationDto> getAllReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getAllReservationsByUserId(userId);
    }
}
