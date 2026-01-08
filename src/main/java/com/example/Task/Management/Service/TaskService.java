package com.example.Task.Management.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.annotations.Collate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task.Management.DTO.TaskDTO;
import com.example.Task.Management.Entity.Task;
import com.example.Task.Management.Enum.TaskStatus;
import com.example.Task.Management.Repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public TaskDTO createTask(TaskDTO dto) {
		
		Task task = new Task();
		
		task.setTaskTitle(dto.getTaskTitle());
		task.setTaskDescriptions(dto.getTaskDescription());
		task.setAssignedToEmail(dto.getAssignedToEmail());
		task.setAssignedAt(LocalDateTime.now());
		task.setDueDate(dto.getDueDate());
		task.setPriority(dto.getPriorty());
		task.setTaskStatus(dto.getTaskStatus());
		
		taskRepository.save(task);
		
		return toDTO(task);
	}
	
	public List<TaskDTO>getAllTask(){
		
		return taskRepository.findAll().stream().map(this:: toDTO).collect(Collectors.toList());
	}
	
	public List<TaskDTO>getTaskByUser(String assignedToEmail){
		return taskRepository.FindByAssignedToEmail(assignedToEmail).stream().map(this:: toDTO).collect(Collectors.toList());
	}
	
	public TaskDTO updateTaskStatus(Long id,TaskStatus taskStatus) {
		Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("Task not found"));
		task.setTaskStatus(taskStatus);
		taskRepository.save(task);
		
		return toDTO(task);
	}
	
	private TaskDTO toDTO(Task task) {
		
		TaskDTO dto = new TaskDTO();
		
		dto.setTaskTitle(task.getTaskTitle());
		dto.setTaskDescription(task.getTaskDescriptions());
		dto.setAssignedToEmail(task.getAssignedToEmail());
		dto.setAssignedAt(LocalDateTime.now());
		dto.setDueDate(task.getDueDate());
		dto.setPriorty(task.getPriority());
		dto.setTaskStatus(task.getTaskStatus());
		
		return dto;
	}
	
}
