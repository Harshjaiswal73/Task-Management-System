package com.example.Task.Management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.WorkFlow;

@Repository
public interface WorkflowRepository extends JpaRepository<WorkFlow, Long> {

	Optional<WorkFlow>findByName(String name);
	
}
