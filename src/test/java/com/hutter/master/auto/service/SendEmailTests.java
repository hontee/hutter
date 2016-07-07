package com.hutter.master.auto.service;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hutter.master.ApplicationTests;
import com.hutter.master.base.client.EmailClient;

/**
 * 自动发测试邮件发送
 * @author Administrator
 */
public class SendEmailTests extends ApplicationTests {
	
	@Autowired
	private EmailClient client;
	
	@Test
	public void sendEmail() throws MessagingException {
		client.sendText("自动发测试邮件发送", "该内容在执行自动化测试时发送，请勿回复。", "honty77@qq.com");
	}
	
}
