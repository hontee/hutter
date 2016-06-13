package com.hutter.master.base.client;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * Send Email Client.
 * @author larry.qi
 */
@Configuration
public class EmailClient {
	
	@Autowired
	private MailProperties props;
	private JavaMailSender sender;
	
	/**
	 * Create JavaMeailSender Object.
	 * @return
	 */
	private JavaMailSender createJavaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setDefaultEncoding(props.getDefaultEncoding().displayName());
		sender.setHost(props.getHost());
		sender.setPassword(props.getPassword());
		sender.setPort(props.getPort());
		sender.setProtocol(props.getProtocol());
		sender.setUsername(props.getUsername());
		return sender;
	}
	
	/**
	 * Send Email with Text
	 * @param subject
	 * @param text
	 * @param addresses
	 * @throws MessagingException
	 */
	public void sendText(String subject, String text, String address) throws MessagingException {
		send(subject, text, address, false);
	}
	
	/**
	 * Send Email with HTML
	 * @param subject
	 * @param html
	 * @param address
	 * @throws MessagingException
	 */
	public void sendHTML(String subject,String html, String address) throws MessagingException {
		send(subject, html, address, true);
	}

	/**
	 * Send Mail
	 * @param subject
	 * @param text
	 * @param address
	 * @param html
	 * @throws MessagingException
	 */
	private void send(String subject, String text, String address, boolean html) throws MessagingException {
		sender = createJavaMailSender();
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom(props.getProperties().get("from"));
		helper.setTo(address);
		helper.setSubject(subject);
		helper.setText(text, html);
		sender.send(message);
	}
	
}
