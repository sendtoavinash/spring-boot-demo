package com.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mail.serviceImpl.MailSenderService;

@Component
public class MailScheduler {

	@Autowired
	private MailSenderService senderService;

	@Scheduled(cron = "* * * * * *")
	public void scheduleMail() {

		sendMail();

	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() {
		senderService.sendEmail("psingh6196@gmail.com", "Spring Mail", "Blank mail....");

	}
}
