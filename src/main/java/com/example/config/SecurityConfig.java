package com.example.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.auth.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;
	
	
//	SECURITY CONFIGURATION
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()).cors(org.springframework.security.config.Customizer.withDefaults())
				.authorizeHttpRequests(auth -> auth.requestMatchers("api/auth/**","/doctor/**","/file/**").permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).httpBasic().disable();
		return http.build();

	}
//	CORS CONFIGURATION
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(List.of("http://localhost:5173"));
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers","path"));
	    configuration.setAllowCredentials(true);  // if using cookies/token
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}


}
