package com.example.Task.Management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Task.Management.DTO.TaskDTO;
import com.example.Task.Management.Enum.TaskStatus;
import com.example.Task.Management.Service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/createTask")
	public ResponseEntity<TaskDTO>createTask(@RequestBody TaskDTO taskDTO){
		return ResponseEntity.ok(taskService.createTask(taskDTO));
	}
	
	@GetMapping("/allTask")
	public ResponseEntity<List<TaskDTO>>getAllTasks(){
		return ResponseEntity.ok(taskService.getAllTask());
	}
	
	@GetMapping("/user/{userEmail}")
	public ResponseEntity<List<TaskDTO>>getTaskByUser(@RequestParam String assignedToEmail){
		return ResponseEntity.ok(taskService.getTaskByUser(assignedToEmail));
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<TaskDTO>updateStatus(@PathVariable Long id,@RequestParam TaskStatus taskStatus){
		return ResponseEntity.ok(taskService.updateTaskStatus(id, taskStatus));
	}
	
	@GetMapping("/public/test")
	public String testpublic() {
		return"Task API is Running";
	}
}
