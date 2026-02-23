package com.example.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.user.DTO.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtUtils jwtUtils;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = null;
		String token = null;
		String username = null;
		
		try {
			header = request.getHeader("Authorization");
			if(header != null && header.startsWith("Bearer ")) {
				token = header.substring(7);
				System.out.println("token : "+token);
				
			}
			if(token  != null) {
				username= this.jwtUtils.extractUsername(token);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
				if (jwtUtils.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		filterChain.doFilter(request, response);
	}

	

}
