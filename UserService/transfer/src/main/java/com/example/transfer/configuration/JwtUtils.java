package com.example.transfer.configuration;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	@Value("${jwt.secret}")
	private String jwtSecret;
	@Value("${jwt.jwtExpirationInMs}")
	private int jwtExpirationInMs;
	
	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
	}
	
public  String generateToken(String email,Map<String, Object> claims) {
	return Jwts.builder()
			.setSubject(email)
			.setClaims(claims)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis()+jwtExpirationInMs))
			.signWith(getSigningKey())
			.compact();
}

public Claims getClaimsFromToken(String token) {
	return Jwts.parserBuilder()
			.setSigningKey(getSigningKey())
			.build()
			.parseClaimsJws(token)
			.getBody();
	
	
}

public boolean validateToken(String token) {
try {
	Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
	return true;
}
catch(Exception e) {
	return false;
}
	
}

public int jwtExpirationInMs() {
	return jwtExpirationInMs;
}
}
