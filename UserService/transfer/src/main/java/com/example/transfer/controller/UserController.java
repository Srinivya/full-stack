package com.example.transfer.controller;

import com.example.transfer.configuration.JwtUtils;
import com.example.transfer.exception.NotValidDomain;
import com.example.transfer.model.*;
import com.example.transfer.service.UserService;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    
@Autowired
private JwtUtils jwtutils;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
  System.out.println("What happen");
            if (!userService.isGeekBaseDomain(signupRequest.getEmail())) {
               throw new NotValidDomain("Only geekbase Domain allowed");
            }

            userService.signup(signupRequest.getEmail(), signupRequest.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "success", true,
                "message", "Signup successful! Please verify your email."
            ));
        
    }

    
    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpVerificationRequest otpRequest) {
      
            userService.verifyOtp(otpRequest.getEmail(), otpRequest.getOtp());

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Email verified successfully."
            ));
            
    };
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
       
            User user= userService.login(loginRequest.getEmail(), loginRequest.getPassword());
            Map<String, Object> payload = Map.of(
            		"email",user.getEmail(),
            		"Id",user.getId()
            		);
            String token = jwtutils.generateToken(loginRequest.getEmail(), payload);
            System.out.println("Generated Token: " + token);
            
            
            Cookie cookie=new Cookie("jwt",token);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge((int) (jwtutils.jwtExpirationInMs() / 1000));
            response.addCookie(cookie);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful!",
                "token", token
            ));
            
      
    }


    @PostMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(
            @RequestParam String email,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phone) {

    
            userService.updateUserProfileByEmail(email, name, address, phone);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Profile updated successfully."
            ));
  
    }
    
    @GetMapping("/viewProfile")
    public ResponseEntity<?> getProfile(@RequestParam String email) {
     
            return ResponseEntity.ok(userService.getUserProfileByEmail(email));
        
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String message = userService.logout(session);

        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", message
        ));
    }
  

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test successful!";
    }
}


