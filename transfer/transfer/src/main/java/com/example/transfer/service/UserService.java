package com.example.transfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.transfer.model.User;
import com.example.transfer.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String signup(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            return "Email is already registered.";
        }

        String generatedOtp = emailService.sendOtp(email);

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password)); 
        user.setOtp(generatedOtp);
        user.setVerified(false); 
        userRepository.save(user);

        return "Signup successful! Please verify your email.";
    }

    public String login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return "User not found."; 
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Invalid credentials."; 
        }

        return "Login successful!"; 
    }

    public String verifyOtp(String email, String otp) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            return "User not found."; 
        }

        User user = userOpt.get();

        if (user.getOtp().equals(otp)) {
            user.setVerified(true);  
            userRepository.save(user);
            return "Email verified successfully.";
        } else {
            return "Invalid OTP."; 
        }
    }

    public boolean isGeekBaseDomain(String email) {
        return email != null && email.endsWith("@geekbase.in");
    }
}
