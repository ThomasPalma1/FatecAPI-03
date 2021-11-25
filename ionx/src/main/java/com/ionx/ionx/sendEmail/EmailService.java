package com.ionx.ionx.sendEmail;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailService {

	public JavaMailSenderImpl javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("nosso-email");
		mailSender.setPassword("nossa-senha");

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		mailSender.setJavaMailProperties(props);

		return mailSender;
	}

	public void enviarEmailSimples(String titulo, String corpoMensagem, String destinatario) {
		JavaMailSenderImpl javaMailSender = javaMailSender();
		SimpleMailMessage email = new SimpleMailMessage();

		email.setSubject(titulo);
		email.setText(corpoMensagem);
		email.setTo(destinatario);
		email.setFrom("ionx@gmail.com");
		javaMailSender.send(email);
	}
}