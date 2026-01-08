package com.example.user.DTO;

public class OTPVerificationRequest {
	private String username;
	private String otp;
	public OTPVerificationRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OTPVerificationRequest(String username, String otp) {
		super();
		this.username = username;
		this.otp = otp;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	

}
