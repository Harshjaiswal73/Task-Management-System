package com.example.Task.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Task.Management.DTO.UserProfileDTO;
import com.example.Task.Management.Service.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/userProfile")
@RequiredArgsConstructor
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;
	
	@PostMapping("/createProfile")
	public ResponseEntity<UserProfileDTO> createProfile(@RequestBody UserProfileDTO dto){
		
		return ResponseEntity.ok(userProfileService.createProfile(dto));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<UserProfileDTO>>getAllprofile(){
		return ResponseEntity.ok(userProfileService.getAllProfile());
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<UserProfileDTO>getProfileByEmail(@PathVariable String userOrganizationEmail){
		
		return ResponseEntity.ok(userProfileService.getProfileByOrganizationEmail(userOrganizationEmail));
	}
	
	@PutMapping("/update/{email}")
	public ResponseEntity<UserProfileDTO>update(@PathVariable String userOrganizationEmail,@RequestBody UserProfileDTO dto){
		
		return ResponseEntity.ok(userProfileService.updateProfile(userOrganizationEmail, dto));
	}
	
	@GetMapping("/public/test")
	public String publicTest() {
		return "User Profile API is Running fine";
	}
}
