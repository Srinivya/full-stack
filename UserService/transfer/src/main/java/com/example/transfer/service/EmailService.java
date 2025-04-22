package com.example.transfer.service;

import com.example.transfer.model.EmailDetails;

public interface EmailService {

    String sendOtp(String recipient);

    String sendSimpleMail(EmailDetails details);  
}
