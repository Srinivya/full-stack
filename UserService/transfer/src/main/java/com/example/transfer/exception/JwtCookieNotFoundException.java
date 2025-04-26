package com.example.transfer.exception;

public class JwtCookieNotFoundException extends RuntimeException  {
public JwtCookieNotFoundException(String msg) {
	super(msg);
}
}
