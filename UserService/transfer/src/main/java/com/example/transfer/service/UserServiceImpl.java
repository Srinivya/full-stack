package com.example.transfer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.transfer.DTO.UserProfileDTO;
import com.example.transfer.exception.InvalidCredentialsException;
import com.example.transfer.exception.InvalidOtpException;
import com.example.transfer.exception.SessionNotFoundException;
import com.example.transfer.exception.UserNotFoundException;
import com.example.transfer.model.User;
import com.example.transfer.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
@Service
public class UserServiceImpl implements UserService {
	   @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private EmailServiceImpl emailService;

	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    @Override
	    public void signup(String email, String password) {
	        Optional<User> userOpt = userRepository.findByEmail(email);

	        if (userOpt.isPresent()) {
	            throw new IllegalArgumentException("Email is already registered.");
	        }

	        String generatedOtp = emailService.sendOtp(email);

	        User user = new User();
	        user.setEmail(email);
	        user.setPassword(passwordEncoder.encode(password));
	        user.setOtp(generatedOtp);
	        user.setVerified(false);

	        userRepository.save(user);
	    }
	    @Override
	    public User login(String email, String password) {
	        Optional<User> userOpt = userRepository.findByEmail(email);

	        if (!userOpt.isPresent()) {
	        	  throw new UserNotFoundException("User not found with email: "+email);
	        }

	        User user = userOpt.get();

	        if (!passwordEncoder.matches(password, user.getPassword())) {
	            throw new InvalidCredentialsException("Invalid credentials.");
	        }
	        return user;
	    }
	    @Override
	    public void verifyOtp(String email, String otp) {
	        Optional<User> userOpt = userRepository.findByEmail(email);

	        if (!userOpt.isPresent()) {
	            throw new UserNotFoundException("User not found with email: "+email);
	        }

	        User user = userOpt.get();

	        if (!user.getOtp().equals(otp)) {
	            throw new InvalidOtpException("Invalid OTP .");
	        }

	        user.setVerified(true);
	        userRepository.save(user);
	    }
	    @Override
	    public boolean isGeekBaseDomain(String email) {
	        return email != null && email.endsWith("@geekbase.in");
	    }
	    @Override
	    public void updateUserProfileByEmail(String email, String name, String address, String phone) {
	        Optional<User> userOpt = userRepository.findByEmail(email);

	        if (!userOpt.isPresent()) {
	        	 throw new UserNotFoundException("User not found with email: "+email);
	        }

	        User user = userOpt.get();

	        if (name != null && !name.trim().isEmpty()) {
	            user.setName(name);
	        }
	        if (address != null && !address.trim().isEmpty()) {
	            user.setAddress(address);
	        }
	        if (phone != null && !phone.trim().isEmpty()) {
	            user.setPhone(phone);
	        }

	        userRepository.save(user);
	    }
	    @Override
	    public String logout(HttpSession session) {
	        if (session != null) {
	            session.invalidate();
	            return "Logout successful.";
	        }
	        throw new SessionNotFoundException("No active session found.");
	    }

	    @Override
	    public UserProfileDTO getUserProfileByEmail(String email) {
	    	User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found."));
	        return new UserProfileDTO(user);
	    }
}
