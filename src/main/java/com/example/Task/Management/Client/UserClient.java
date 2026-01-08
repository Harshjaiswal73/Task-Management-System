package com.example.Task.Management.Client;

import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Task.Management.Enum.Role;

@FeignClient(name="user_service")
public interface UserClient {

	@GetMapping("/api/users/{userofficialEmail}/roles")
	Set<Role> getRole(@PathVariable String userOfficialEmail);
	
}
