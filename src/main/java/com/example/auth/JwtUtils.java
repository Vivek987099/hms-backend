package com.example.auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

	private String SECRET_KEY = "SJDLFJSKLJFS85239851452369521545";

	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole());
		claims.put("username", user.getUsername());
		claims.put("user_id", user.getId());
		return createToken(claims, user.getUsername());
	}

	public String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)).setHeaderParam("typ", "JWT")
				.signWith(getSecretKey()).compact();
	}
	
//	code for further request after login 
	
	@SuppressWarnings("unused")
	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
	}
	
	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}
	
	public Date extractExpiration(String token) {
		return extractAllClaims(token).getExpiration();
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	
	public boolean validateToken(String token,UserDetails userDetails) {
		final String extractedUsername= extractUsername(token);
		return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
