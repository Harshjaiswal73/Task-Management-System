package com.example.Task.Management.DTO;

import java.time.LocalDateTime;

import com.example.Task.Management.Enum.PriorityTask;
import com.example.Task.Management.Enum.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTO {

	private String taskTitle;
	private String taskDescription;
	private String assignedToEmail;
	private LocalDateTime assignedAt;
	private LocalDateTime dueDate;
	private TaskStatus taskStatus;
	private PriorityTask priorty;
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getAssignedToEmail() {
		return assignedToEmail;
	}
	public void setAssignedToEmail(String assignedToEmail) {
		this.assignedToEmail = assignedToEmail;
	}
	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}
	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	public PriorityTask getPriorty() {
		return priorty;
	}
	public void setPriorty(PriorityTask priorty) {
		this.priorty = priorty;
	}
}
