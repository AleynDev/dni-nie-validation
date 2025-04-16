package com.example.dni_validation.controllers;

import com.example.dni_validation.controllers.request.UserRegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing user-related operations.
 * <p>
 * This controller provides an endpoint for user registration.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Registers a new user.
     *
     * Validates the incoming user registration request and processes it.
     * If the input data is valid, returns a 200 OK response.
     *
     * @param request the user registration data, validated using Jakarta Bean Validation
     * @return a ResponseEntity with HTTP status 200 if the registration is successful
     */
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserRegistrationRequest request) {
        // Registration logic...
        return ResponseEntity.ok().build();
    }

}
