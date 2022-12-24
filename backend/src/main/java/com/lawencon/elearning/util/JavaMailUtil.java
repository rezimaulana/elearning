package com.lawencon.elearning.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lawencon.elearning.pojo.EmailPojo;

@Service
public class JavaMailUtil {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(final EmailPojo createdAccountPojo) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(createdAccountPojo.getEmail());
		msg.setSubject(createdAccountPojo.getSubject());
		msg.setText(createdAccountPojo.getBody());
		javaMailSender.send(msg);
	}
	
}