package com.example.otp_auth;

import java.util.List;

public interface UserService {
	  void saveUser(UserDto userDto);
	    User findUserByEmail(String email);
	    List<UserDto> findAllUsers();
}
