package com.example.transfer.service;

import com.example.transfer.DTO.UserProfileDTO;
import com.example.transfer.model.User;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	void signup(String email, String password);
	User login(String email, String password);
	void verifyOtp(String email, String otp);
	boolean isGeekBaseDomain(String email);
	void updateUserProfileByEmail(String email, String name, String address, String phone);
	 String logout(HttpSession session);
	 UserProfileDTO getUserProfileByEmail(String email);
}
