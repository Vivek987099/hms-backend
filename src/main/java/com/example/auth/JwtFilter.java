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
import jakarta.servlet.http.Cookie;
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

		String header = request.getHeader("Authorization");
		String jwt = null;
		String username = null;
		
		 jwt = extractTokenFromCookie(request);
		if (jwt==null && header != null && header.startsWith("Bearer ")) {
			jwt = header.substring(7);
		}
		if (jwt != null) {
	        try {
	            username = jwtUtils.extractUsername(jwt);
	        } catch (Exception e) {
	            // Token expire ya invalid ho sakta hai, ignore karein taaki 403 aaye
	            System.out.println("Token validation failed: " + e.getMessage());
	        }
	    }
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			if (jwtUtils.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}

	public String extractTokenFromCookie(HttpServletRequest httpServletRequest) {
		if (httpServletRequest.getCookies() == null)
			return null;
		for (Cookie cookie : httpServletRequest.getCookies()) {
			if (cookie.getName().equals("token")) {
				return cookie.getValue();
			}

		}
		return null;
	}

}
