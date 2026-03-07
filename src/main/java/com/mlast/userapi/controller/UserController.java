package com.mlast.userapi.controller;

import com.mlast.userapi.dto.request.CreateUserRequest;
import com.mlast.userapi.dto.request.UpdateUserRequest;
import com.mlast.userapi.dto.response.UserResponse;
import com.mlast.userapi.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST /api/v1/users — Create a new user.
     */
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse created = userService.createUser(request);
        URI location = URI.create("/api/v1/users/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    /**
     * GET /api/v1/users/{id} — Get user by ID.
     */
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * GET /api/v1/users — List users (paginated) or search by query.
     */
    @GetMapping
    public Page<UserResponse> listUsers(
            @RequestParam(required = false) String search,
            @PageableDefault(size = 20, sort = "id") Pageable pageable) {

        if (search != null && !search.isBlank()) {
            return userService.searchUsers(search.strip(), pageable);
        }
        return userService.listUsers(pageable);
    }

    /**
     * PUT /api/v1/users/{id} — Update user fields (partial update).
     */
    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id,
                                   @Valid @RequestBody UpdateUserRequest request) {
        return userService.updateUser(id, request);
    }

    /**
     * DELETE /api/v1/users/{id} — Delete a user.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * PATCH /api/v1/users/{id}/enable — Enable or disable a user account.
     */
    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enableUser(@PathVariable Long id,
                           @RequestParam boolean enabled) {
        userService.enableUser(id, enabled);
    }

    @GetMapping("/export")
    @PreAuthorize("hasRole('ADMIN')")
    public void exportUsers(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=users.csv");
        userService.exportUsers(response.getWriter());
    }
}
