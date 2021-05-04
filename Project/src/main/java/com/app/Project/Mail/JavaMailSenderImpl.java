package com.app.Project.Mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;

public class JavaMailSenderImpl {
	@Bean
	public JavaMailSenderImpl getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	    
	    mailSender.setUsername("rashichhajer1@gmail.com");
	    mailSender.setPassword("atsvwowtbbsshyup");
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
	    return mailSender;
	}

	private Properties getJavaMailProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	private void setPassword(String string) {
		// TODO Auto-generated method stub
		
	}

	private void setUsername(String string) {
		// TODO Auto-generated method stub
		
	}

	private void setPort(int i) {
		// TODO Auto-generated method stub
		
	}

	private void setHost(String string) {
		// TODO Auto-generated method stub
		
	}
}
