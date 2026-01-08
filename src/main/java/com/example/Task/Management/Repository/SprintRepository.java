package com.example.Task.Management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.Sprint;
import com.example.Task.Management.Enum.SprintState;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
	
	List<Sprint>findByProjectId(Long projectId);
	List<Sprint>findBySprintState(SprintState sprintState);
}
