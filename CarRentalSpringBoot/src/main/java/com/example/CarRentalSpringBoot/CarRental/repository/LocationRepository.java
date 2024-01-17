package com.example.CarRentalSpringBoot.CarRental.repository;

import com.example.CarRentalSpringBoot.CarRental.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
