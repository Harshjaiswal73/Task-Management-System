package com.example.Task.Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Task.Management.DTO.EmailLogDTO;
import com.example.Task.Management.Service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notify")
public class NotificationController {

	@Autowired
	private NotificationService notification;
	
	@PostMapping("/send")
	public ResponseEntity<String>sendEmail(@RequestBody EmailLogDTO dto){
		
		notification.sendEmail(dto);
		
		return ResponseEntity.ok("Email sent Successfully");
		
	}
	
}
