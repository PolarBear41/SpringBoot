package com.example.CarRentalSpringBoot.service;

import com.example.CarRentalSpringBoot.CarRental.entity.User;
import com.example.CarRentalSpringBoot.CarRental.pojo.dto.UserDto;
import com.example.CarRentalSpringBoot.CarRental.repository.UserRepository;
import com.example.CarRentalSpringBoot.CarRental.service.impl.UserServiceImpl;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = new User();
        userDto = new UserDto("test", "test", "test@gmail.com", "test", "test", "test");
    }

    @Test
    void createUser() {
        userService.createUser(userDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getUserById() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        UserDto result = userService.getUserById(userId);

        assertNotNull(result);
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<UserDto> result = userService.getAllUsers();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}

