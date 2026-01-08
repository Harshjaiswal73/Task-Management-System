package com.example.Task.Management.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.stereotype.Service;

import com.example.Task.Management.DTO.UserProfileDTO;
import com.example.Task.Management.Entity.UserProfile;
import com.example.Task.Management.Repository.UserProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	
	
	public UserProfileDTO createProfile(UserProfileDTO dto) {
		
		if(userProfileRepository.findByUserOrganizationEmail(dto.getUserOrganizationEmail()).isPresent()) {
			throw new RuntimeException("Profile already Exists:"+dto.getUserOrganizationEmail());
		}
		
		UserProfile user = new UserProfile();
		
		
		user.setUserOrganizationEmail(dto.getUserOrganizationEmail());
		user.setDepartment(dto.getDepartment());
		user.setDesignation(dto.getDesignation());
		user.setOrganizationName(dto.getOrganizationName());
		user.setCreatedAt(LocalDateTime.now());
		user.setActive(true);
		
		
		userProfileRepository.save(user);
		
		return toDTO(user);
	}
	
	public List<UserProfileDTO>getAllProfile(){
		return userProfileRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public UserProfileDTO getProfileByOrganizationEmail(String userOreganizationEmail) {
		
		UserProfile user = userProfileRepository.findByUserOrganizationEmail(userOreganizationEmail).orElseThrow(()-> new RuntimeException("User not found"));
		
		return toDTO(user);
	}
	
	public UserProfileDTO updateProfile(String userOrganizationEmail,UserProfileDTO dto) {
		UserProfile user = userProfileRepository.findByUserOrganizationEmail(userOrganizationEmail).orElseThrow(()->new RuntimeException ("user not found"));
		
		user.setUsername(dto.getUserName());
		user.setUserOrganizationEmail(dto.getUserOrganizationEmail());
		user.setDepartment(dto.getDepartment());
		user.setDesignation(dto.getDesignation());
		user.setOrganizationName(dto.getOrganizationName());
		user.setCreatedAt(LocalDateTime.now());
		user.setActive(dto.isActive());
		
		userProfileRepository.save(user);
		
		return toDTO(user);
	}
	
	private UserProfileDTO toDTO(UserProfile user) {
		UserProfileDTO dto = new UserProfileDTO();
		
		dto.setUserName(user.getUsername());
		dto.setUserOrganizationEmail(user.getUserOrganizationEmail());
		dto.setDepartment(user.getDepartment());
		dto.setDesignation(user.getDesignation());
		dto.setOrganizationName(user.getOrganizationName());
		dto.setCreatedAt(LocalDateTime.now());
		dto.setActive(user.isActive());
		
		return dto;
	}
	
}
