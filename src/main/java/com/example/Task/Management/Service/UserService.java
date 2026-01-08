package com.example.Task.Management.Service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Task.Management.DTO.AuthenticationResponseDTO;
import com.example.Task.Management.DTO.LoginRequestDTO;
import com.example.Task.Management.DTO.RegisterRequestDTO;

import com.example.Task.Management.Entity.User;
import com.example.Task.Management.Enum.Permission;
import com.example.Task.Management.Enum.Role;
import com.example.Task.Management.Repository.UserRespository;
import com.example.Task.Management.Security.JWTUtil;
import com.example.Task.Management.Security.PermissionConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private PermissionConfig permissionConfig;
	
	public AuthenticationResponseDTO register(RegisterRequestDTO request) {
		
		Optional<User>existing=userRespository.findByUserEmail(request.getUserEmail());
		if(existing.isPresent()) {
			throw new RuntimeException("User already exist");
		}
		
		User user = new User();
		user.setUsername(request.getUserName());
		user.setUserEmail(request.getUserEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		userRespository.save(user);
		
		String token = jwtUtil.generateToken(user.getUserEmail());
		
		return new AuthenticationResponseDTO(token,"User register successfully");
	}
	
	public String login(LoginRequestDTO dto) {
		User user = userRespository.findByUserEmail(dto.getUserEmail()).orElseThrow(()-> new RuntimeException("User not found"));
		if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}
		return jwtUtil.generateToken(user.getUserEmail());
	}
	
	
//	public User updateRole(Long id,Role role) {
//		User user = userRespository.findById(id).orElseThrow(()-> new RuntimeException("user not found"));
//		Role newRole = Role.valueOf(role.name());
//		user.setRole(newRole);
//		return userRespository.save(user);
//	}
//	
//	public void deleteUser(Long id,User user) {
//		Set<Permission> perm  = permissionConfig.getrolePermission().get(user.getRole());
//		
//		if(!perm.contains(Permission.USER_MANAGE)) {
//			throw new RuntimeException("Acess denied");
//		}
//		userRespository.save(user);
//	}
	
}
