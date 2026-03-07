package com.mlast.userapi.service.impl;

import com.mlast.userapi.dto.request.CreateUserRequest;
import com.mlast.userapi.dto.request.UpdateUserRequest;
import com.mlast.userapi.dto.response.UserResponse;
import com.mlast.userapi.entity.User;
import com.mlast.userapi.exception.DuplicateResourceException;
import com.mlast.userapi.exception.ResourceNotFoundException;
import com.mlast.userapi.mapper.UserMapper;
import com.mlast.userapi.repository.UserRepository;
import com.mlast.userapi.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PrintWriter;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("User", "email", request.email());
        }

        User user = userMapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.password()));

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = findUserOrThrow(id);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return userMapper.toResponse(user);
    }

    @Override
    public Page<UserResponse> listUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponse);
    }

    @Override
    public Page<UserResponse> searchUsers(String query, Pageable pageable) {
        return userRepository.search(query, pageable)
                .map(userMapper::toResponse);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = findUserOrThrow(id);

        if (request.firstName() != null) {
            user.setFirstName(request.firstName());
        }
        if (request.lastName() != null) {
            user.setLastName(request.lastName());
        }
        if (request.email() != null && !request.email().equals(user.getEmail())) {
            if (userRepository.existsByEmail(request.email())) {
                throw new DuplicateResourceException("User", "email", request.email());
            }
            user.setEmail(request.email());
        }

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = findUserOrThrow(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void enableUser(Long id, boolean enabled) {
        User user = findUserOrThrow(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

    private User findUserOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
    }

    @Override
    public void exportUsers(PrintWriter writer) {
        writer.println("id,firstName,lastName,email,role,enabled");
        userRepository.findAll().forEach(user ->
                writer.printf("%d,%s,%s,%s,%s,%s%n",
                        user.getId(), user.getFirstName(), user.getLastName(),
                        user.getEmail(), user.getRole(), user.isEnabled()));
    }
}
