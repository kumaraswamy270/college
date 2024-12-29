package com.student.detail.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.student.detail.model.EmailRequest;

@Controller
@RequestMapping("/send-gmail")
public class GmailController {

	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping("/gmail")
	public String showGmailForm() {
		return "email";
	}

	@PostMapping
	public ResponseEntity<Map<String, String>> sendGmail(@RequestBody EmailRequest emailRequest) {
		Map<String, String> response = new HashMap<>();
		try {
			// Create and send the email
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(emailRequest.getToEmail());
			message.setSubject(emailRequest.getSubject());
			message.setText(emailRequest.getMessage());
			mailSender.send(message);

			// Success response
			response.put("status", "success");
			response.put("message", "Email sent successfully!");
			return ResponseEntity.ok(response);

		} catch (Exception e) {
			e.printStackTrace();
			// Failure response
			response.put("status", "failure");
			response.put("message", "Error occurred while sending email.");
			return ResponseEntity.status(500).body(response);
		}
	}
}
