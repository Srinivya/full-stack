package com.example.transfer.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.transfer.exception.InvalidTokenException;
import com.example.transfer.exception.JwtCookieNotFoundException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;
	
	private String getJwtFromCookies(HttpServletRequest request) {
		 if (request.getCookies() != null) {
	            for (var cookie : request.getCookies()) {
	                if ("jwt".equals(cookie.getName())) {
	                	
	                    return cookie.getValue();
	                }
	            }
	        }
		  throw new JwtCookieNotFoundException("JWT cookie not found in the request.");
		} @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {

	        String token = getJwtFromCookies(request);

	        if (token != null) {
	            try {
	                Claims claims = jwtUtils.getClaimsFromToken(token);
	                request.setAttribute("userId", claims.get("userId"));
	            } catch (ExpiredJwtException e) {
	               throw new InvalidTokenException("Invalid or expired token.");
	            }
	            catch (MalformedJwtException e) {
	               
	                throw new InvalidTokenException("The token is malformed.");
	            }
	        } else {
	           throw new JwtCookieNotFoundException("JWT cookie not found in the request.");
	        }
	       
	        filterChain.doFilter(request, response);
	      
	    }
	
	
}
