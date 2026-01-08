package com.example.Task.Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.Task.Management.DTO.AuthenticationResponseDTO;
import com.example.Task.Management.DTO.LoginRequestDTO;
import com.example.Task.Management.DTO.RegisterRequestDTO;
import com.example.Task.Management.Security.JWTUtil;
import com.example.Task.Management.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserAuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
//	@PostMapping("/register")
//	public ResponseEntity<?> register(@RequestBody UserRegisterDTO dto){
//		
//		return  ResponseEntity.ok(authService.register(dto));
//		
//	}
	
	@PostMapping("/register")
	public ResponseEntity<String>register(@RequestBody RegisterRequestDTO dto){
		userService.register(dto);
		//u.getPassword().hashCode();
		return ResponseEntity.ok("Register successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponseDTO>login(@RequestBody LoginRequestDTO dto){
		String token = userService.login(dto);
		return ResponseEntity.ok(new AuthenticationResponseDTO(token,"login successfully"));
		//return ResponseEntity.ok(new AuthenticationResponseDTO(token,"Login successfully"));
	}
	
//	@PutMapping("/updateUser")
//	public ResponseEntity<User>updateuser(@PathVariable Long id,@RequestParam Role role){
//		return ResponseEntity.ok(userService.updateRole(id, role));
//	}
//	
//	@DeleteMapping("/deleteuser")
//	public ResponseEntity<?>deleteUser(@PathVariable Long id,@RequestBody User user){
//		userService.deleteUser(id,user);
//		return ResponseEntity.ok("User deleted successfully");
//	}
	
	@GetMapping("/welcome")
	public ResponseEntity<String>welcome(){
		return ResponseEntity.ok("authentication working fine");
	}
	
}
