package com.example.mailSend;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendSimpleMail(Entity_clas details) {
        try {
            if (!details.getEmail().toLowerCase().endsWith("gmail.com")) {
                return "Only Geekbase domain login allowed.";
            }

            SecureRandom random = new SecureRandom();
            int otp = 100000 + random.nextInt(900000);
            details.setOtp(otp); 

            String otpMessage = "The OTP is: " + otp + "\n\n" + details.getMsgBody();

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(details.getEmail());
            message.setSubject(details.getSubject());
            message.setText(otpMessage);

            javaMailSender.send(message);

            System.out.println("Generated OTP: " + otp);

            return "Mail sent successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error While sending mail";
        }
    }
}
