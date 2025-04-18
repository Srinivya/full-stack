package com.example.mailSend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
    @Autowired
    private UserRepository repository;
    
    private final EmailServiceImpl emailService;

   
    @Autowired
    public UserService(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    public Entity_clas saveUser(Entity_clas user) {
        return repository.save(user);
    }

    public Entity_clas getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public String registerUser(Entity_clas userDetails) {
        Entity_clas existingUser = getUserByEmail(userDetails.getEmail());
        if (existingUser != null) {
            return "User already exists with this email."; 
        }
        saveUser(userDetails);
        return emailService.sendSimpleMail(userDetails); 
    }

    public String validateOtp(String email, int enteredOtp) {
        Entity_clas user = getUserByEmail(email);
        if (user == null) {
            return "User not found!";
        }
        if (user.getOtp() == enteredOtp) {
            return "OTP validated successfully!"; 
        } else {
            return "Invalid OTP!";
        }
    }
}
