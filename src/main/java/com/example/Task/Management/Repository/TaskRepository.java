package com.example.Task.Management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>  {

	List<Task>FindByAssignedToEmail(String assignedToEmail);
}
