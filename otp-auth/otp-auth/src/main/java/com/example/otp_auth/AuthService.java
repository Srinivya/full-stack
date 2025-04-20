package com.example.otp_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OTPService otpService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String generateAndSendOTP(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            String otp = otpService.generateOTP();
            user.setOtp(otp);
            userRepository.save(user);
            otpService.sendOTP(email, otp);
            return "OTP sent!";
        }
        return "User not found!";
    }

    public String validateOTP(String email, String otp) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getOtp().equals(otp)) {
            return "OTP validated successfully!";
        }
        return "Invalid OTP!";
    }

    public String login(User loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return "Login successful!";
        }
        return "Invalid credentials!";
    }
}
