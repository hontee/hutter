package com.hutter.master.base.client;

import javax.mail.MessagingException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hutter.master.HutterApplicationTests;

public class EmailClientTests extends HutterApplicationTests {

	@Autowired
	private EmailClient client;
	
	@Test
	public void sendEmail() throws MessagingException {
		client.sendText("测试邮箱名", "body content.", "honty77@qq.com");
	}
}
