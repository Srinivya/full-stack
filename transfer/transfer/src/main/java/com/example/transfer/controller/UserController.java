package com.example.transfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.transfer.model.LoginRequest;
import com.example.transfer.model.OtpVerificationRequest;
import com.example.transfer.model.SignupRequest;
import com.example.transfer.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        System.out.println("Received email: " + signupRequest.getEmail()); 
        
        if (!userService.isGeekBaseDomain(signupRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only geekbase addresses are allowed.");
        }

        String response = userService.signup(signupRequest.getEmail(), signupRequest.getPassword());
        return response.startsWith("Signup successful!")
            ? ResponseEntity.status(HttpStatus.CREATED).body(response)
            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest otpRequest) {
        String response = userService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());
        return response.equals("Email verified successfully.")
            ? ResponseEntity.status(HttpStatus.OK).body(response)
            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test successful!";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String response = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return response.equals("Login successful!")
            ? ResponseEntity.status(HttpStatus.OK).body(response)
            : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
