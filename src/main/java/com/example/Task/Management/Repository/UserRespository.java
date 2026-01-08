package com.example.Task.Management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.User;

@Repository
public interface UserRespository extends JpaRepository<User,String> {

  Optional<User>findByUserEmail(String userEmail);
  
  boolean existsByUserEmail(String userEmail);
	
}
