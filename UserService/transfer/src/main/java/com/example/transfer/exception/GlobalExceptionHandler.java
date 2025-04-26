package com.example.transfer.exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handle404(NoHandlerFoundException ex) {
        return ResponseEntity.status(404).body(Map.of(
            "status", 404,
            "error", "Not Found",
            "message", "The requested API endpoint does not exist",
            "path", ex.getRequestURL()
        ));
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of(
            "status", 404,
            "error", "User Not Found",
            "message", ex.getMessage()
        ));
    }
    @ExceptionHandler(NotValidDomain.class)
    public ResponseEntity<?> NotValidDomain(NotValidDomain ex) {
        return ResponseEntity.status(422).body(Map.of(
            "status", 422,
            "error", "Invalid Email Domain",
            "message", ex.getMessage()
        ));
    }
    @ExceptionHandler(SessionNotFoundException.class)
    public ResponseEntity<?> handleSessionNotFound(SessionNotFoundException ex) {
        return ResponseEntity.status(401).body(Map.of(
            "status", 401,
            "error", "Session Not Found",
            "message", ex.getMessage()
        ));
    }
    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<?> handleInvalidOtp(InvalidOtpException ex) {
        return ResponseEntity.status(400).body(Map.of(
            "status", 400,
            "error", "Invalid OTP",
            "message", ex.getMessage()
        ));
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<?> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(401).body(Map.of(
            "status", 401,
            "error", "Invalid Credentials",
            "message", ex.getMessage()
        ));
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<?> InvalidTokenException(InvalidTokenException ex) {
        return ResponseEntity.status(401).body(Map.of(
            "status", 401,
            "error", "Invalid Token",
            "message", ex.getMessage()
        ));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity.status(500).body(Map.of(
            "status", 500,
            "error", "Internal Server Error",
            "message", ex.getMessage()
        ));
    }
    
    
    
    
    
}