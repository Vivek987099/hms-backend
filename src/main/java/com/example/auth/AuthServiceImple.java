package com.example.auth;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.user.DTO.CustomUserDetails;
import com.example.user.entity.User;

@Service
public class AuthServiceImple implements AuthService{
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtils jwtUtils;
	@Override
	public ResponseEntity<?> login(LoginRequest loginRequest) {
		 Authentication authentication=  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		  CustomUserDetails  customUserDetails=(CustomUserDetails)  authentication.getPrincipal();
		  		 User user= customUserDetails.getUser();
		  		 String token=  jwtUtils.generateToken(user);
		  		 	ResponseCookie cookie=ResponseCookie.from("token", token).httpOnly(true).secure(false).path("/").sameSite("Lax").maxAge(3600).build();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(Map.of("message","Login successful")) ;
	}
	
	@Override
	public ResponseEntity<?> checkAuth() {
		 Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		 
		 
		 if(auth==null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
			 return ResponseEntity.status(401).body(Map.of("message", "Not authenticated please login now"));
		 }
		    CustomUserDetails customUserDetails= (CustomUserDetails) auth.getPrincipal();
		      User user= customUserDetails.getUser();
		      
		
		// TODO Auto-generated method stub
		return ResponseEntity.ok(Map.of("LoggedIn",true,"username",user.getUsername(),"role",user.getRole()));
	}

}
