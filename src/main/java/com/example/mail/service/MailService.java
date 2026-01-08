package com.example.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public boolean sendMail(String subject,String to,String text) {
		
		boolean status=false;
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setText(text);
		message.setSubject(subject);
		javaMailSender.send(message);
		status=true;
		return status;
	}

	

}
