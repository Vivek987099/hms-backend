package com.example.auth;

public class LoginResponse {
	private String token;
	private String tokenType;
	private int id;
	private String username;
	private String role;

	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}



	public LoginResponse(String token, String tokenType, int id, String username, String role) {
		super();
		this.token = token;
		this.tokenType = tokenType;
		this.id = id;
		this.username = username;
		this.role = role;
	}



	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}



	public String getTokenType() {
		return tokenType;
	}



	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	

}
