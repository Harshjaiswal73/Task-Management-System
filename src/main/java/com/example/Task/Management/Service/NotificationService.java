package com.example.Task.Management.Service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.Task.Management.DTO.EmailLogDTO;
import com.example.Task.Management.Entity.EmailLog;
import com.example.Task.Management.Repository.EmailLogRepository;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	@Autowired
	private EmailLogRepository logRepository;
	
//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	
//	public String sendmail(EmailLogDTO dto) {
//		
//		boolean sentStatus = false;
//		try {
//			MimeMessage message = javaMailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message,true);
//			helper.setTo(dto.getRecipientEmail());
//			helper.setTo(dto.getSubject());
//			helper.setText(dto.getBody(),true);
//			javaMailSender.send(message);
//			sentStatus = true;
//			
//		} catch (MessagingException e) {
//			// TODO: handle exception
//			sentStatus = false;
//		}
//		
//		EmailLog email = new EmailLog(); 
//		logRepository.save(log);
//		
//		return sentStatus ? "Email Sent successfully":"Email sending failed";
//	}
	
	public void sendEmail(EmailLogDTO dto) {
		
		final String fromEmail = "hj336802@gmail.com";
		final String password = "harsh@123";
		
		Properties props = new Properties();
		props.put("mail.smtp.host","smtp.gmail.com");
		props.put("mail.smtp.port","587");
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable","true");
		
		Session session = Session.getInstance(props,new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromEmail));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(dto.getRecipientEmail()));
			msg.setSubject(dto.getSubject());
			msg.setText(dto.getBody());
			
			Transport.send(msg);
			System.out.println("Email sent to"+dto.getRecipientEmail());
		} catch (MessagingException e) {
			// TODO: handle exception
			throw new RuntimeException("Failed to send Email",e);
		}
		
	}
	
}
