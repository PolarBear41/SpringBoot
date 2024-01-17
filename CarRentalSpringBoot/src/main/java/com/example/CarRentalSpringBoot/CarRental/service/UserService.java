package com.example.CarRentalSpringBoot.CarRental.service;

import com.example.CarRentalSpringBoot.CarRental.pojo.dto.UserDto;

import java.util.List;


public interface UserService {
    void createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    void updateUser(Long userId, UserDto userDto);
}
