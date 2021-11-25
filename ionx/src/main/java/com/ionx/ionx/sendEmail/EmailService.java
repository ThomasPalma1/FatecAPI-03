package com.ionx.ionx.sendEmail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailService {
	
	private String host = "smtp.gmail.com";
	private int port = 587;
	private String ourEmail = "ionx@gmail.com";
	private String ourPassword = "nossa-senha";

	private JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(host);
		mailSender.setPort(port);
		mailSender.setUsername(ourEmail);
		mailSender.setPassword(ourPassword);

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(props);

		return mailSender;
	}

	public void sendSimpleEmail(String title, String message, String to) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(title);
		email.setText(message);
		email.setTo(to);
		email.setFrom(ourEmail);
		
		JavaMailSenderImpl javaMailSender = javaMailSender();
		javaMailSender.send(email);
	}
}