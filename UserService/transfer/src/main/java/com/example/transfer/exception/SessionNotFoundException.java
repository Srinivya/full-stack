package com.example.transfer.exception;

public class SessionNotFoundException  extends RuntimeException {
	public SessionNotFoundException(String msg) {
		super(msg);
	}
}
