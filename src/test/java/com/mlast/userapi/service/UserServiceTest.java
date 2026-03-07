package com.mlast.userapi.service;

import com.mlast.userapi.dto.request.CreateUserRequest;
import com.mlast.userapi.dto.response.UserResponse;
import com.mlast.userapi.entity.User;
import com.mlast.userapi.exception.DuplicateResourceException;
import com.mlast.userapi.exception.ResourceNotFoundException;
import com.mlast.userapi.mapper.UserMapper;
import com.mlast.userapi.repository.UserRepository;
import com.mlast.userapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User sampleUser;
    private UserResponse sampleResponse;

    @BeforeEach
    void setUp() {
        sampleUser = new User();
        sampleUser.setId(1L);
        sampleUser.setFirstName("Jane");
        sampleUser.setLastName("Doe");
        sampleUser.setEmail("jane@example.com");
        sampleUser.setPasswordHash("$2a$hashed");

        sampleResponse = new UserResponse(
                1L, "Jane", "Doe", "jane@example.com",
                "USER", true, Instant.now(), Instant.now());
    }

    @Test
    void createUser_success() {
        var request = new CreateUserRequest("Jane", "Doe", "jane@example.com", "securepassword1");

        when(userRepository.existsByEmail("jane@example.com")).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(sampleUser);
        when(passwordEncoder.encode("securepassword1")).thenReturn("$2a$hashed");
        when(userRepository.save(any(User.class))).thenReturn(sampleUser);
        when(userMapper.toResponse(sampleUser)).thenReturn(sampleResponse);

        UserResponse result = userService.createUser(request);

        assertThat(result.email()).isEqualTo("jane@example.com");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void createUser_duplicateEmail_throwsConflict() {
        var request = new CreateUserRequest("Jane", "Doe", "jane@example.com", "securepassword1");
        when(userRepository.existsByEmail("jane@example.com")).thenReturn(true);

        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(DuplicateResourceException.class);
    }

    @Test
    void getUserById_found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(sampleUser));
        when(userMapper.toResponse(sampleUser)).thenReturn(sampleResponse);

        UserResponse result = userService.getUserById(1L);

        assertThat(result.id()).isEqualTo(1L);
    }

    @Test
    void getUserById_notFound_throws() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
