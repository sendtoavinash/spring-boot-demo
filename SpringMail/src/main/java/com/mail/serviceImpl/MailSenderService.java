package com.mail.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("au4ualways@gmail.com");
		message.setTo(to);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);

		System.out.println("Mail Sent Successfully....");

	}
}
