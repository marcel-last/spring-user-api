package com.mlast.userapi.service;

import com.mlast.userapi.dto.request.CreateUserRequest;
import com.mlast.userapi.dto.request.UpdateUserRequest;
import com.mlast.userapi.dto.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.PrintWriter;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(Long id);

    UserResponse getUserByEmail(String email);

    Page<UserResponse> listUsers(Pageable pageable);

    Page<UserResponse> listUsersByEnabled(boolean enabled, Pageable pageable);

    Page<UserResponse> listUsersByRole(String role, Pageable pageable);

    Page<UserResponse> searchUsers(String query, Pageable pageable);

    UserResponse updateUser(Long id, UpdateUserRequest request);

    void deleteUser(Long id);

    void enableUser(Long id, boolean enabled);

    void exportUsers(PrintWriter writer);
}
