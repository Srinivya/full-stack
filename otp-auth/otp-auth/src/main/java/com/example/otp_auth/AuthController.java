package com.example.otp_auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")

public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody User user) {
        User savedUser = authService.signUp(user);
        return ResponseEntity.ok(savedUser != null ? "Sign-up successful!" : "Sign-up failed!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.login(user));
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // or whatever your view name is
    }
   

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        return ResponseEntity.ok(authService.generateAndSendOTP(email));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        return ResponseEntity.ok(authService.validateOTP(email, otp));
    }
}