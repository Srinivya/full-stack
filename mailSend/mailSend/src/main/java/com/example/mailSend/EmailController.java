package com.example.mailSend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") 
//@CrossOrigin(origins = "http://localhost:3000") 
public class EmailController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public String registerUser(@RequestBody Entity_clas userDetails) {
        return userService.registerUser(userDetails);
    }

    @PostMapping("/validateOtp")
    public String validateOtp(@RequestParam String email, @RequestParam int otp) {
        return userService.validateOtp(email, otp); 
    }

    @GetMapping("/test")
    public String testConnection() {
        return "Backend is working!";
    }
}
