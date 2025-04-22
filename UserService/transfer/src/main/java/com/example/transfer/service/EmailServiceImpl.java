package com.example.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.transfer.model.EmailDetails;

import java.util.Random;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendOtp(String recipient) {
        String otp = generateOtp();
        String msgBody = "Your OTP for email verification is: " + otp;

        System.out.println("Generated OTP: " + otp);  

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(recipient);
            mailMessage.setText(msgBody);
            mailMessage.setSubject("Email Verification OTP");
            javaMailSender.send(mailMessage);
            System.out.println("Mail sent to " + recipient);
            return otp;
        } catch (Exception e) {
            e.printStackTrace();  
            return "Error while Sending Mail: " + e.getMessage();
        }
    }


    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "Mail sent successfully!";
        } catch (Exception e) {
            return "Error while sending simple mail: " + e.getMessage();
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
