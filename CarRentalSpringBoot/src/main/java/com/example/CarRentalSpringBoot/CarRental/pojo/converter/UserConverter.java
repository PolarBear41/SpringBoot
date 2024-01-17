package com.example.CarRentalSpringBoot.CarRental.pojo.converter;

import com.example.CarRentalSpringBoot.CarRental.entity.User;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.UserDto;

public class UserConverter {
    public static UserDto userConvertToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .address(user.getAddress())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
    public static User dtoConvertToUser(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}
