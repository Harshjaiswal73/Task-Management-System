package com.example.Task.Management.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import com.example.Task.Management.Enum.PriorityTask;
import com.example.Task.Management.Enum.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String taskTitle;
	
	@Column(length = 2000)
	private String taskDescriptions;
	
	private String assignedToEmail;
	
	private LocalDateTime assignedAt = LocalDateTime.now();
	
	private LocalDateTime dueDate;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	
	@Enumerated(EnumType.STRING)
	private PriorityTask priority;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescriptions() {
		return taskDescriptions;
	}

	public void setTaskDescriptions(String taskDescriptions) {
		this.taskDescriptions = taskDescriptions;
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

	public PriorityTask getPriority() {
		return priority;
	}

	public void setPriority(PriorityTask priority) {
		this.priority = priority;
	}
	
	// chat gpt code 
	@ManyToMany
	@JoinTable(name="task_lables",joinColumns = @JoinColumn(name="lable_id"))
	private Set<Lable> lables = new HashSet<>();
	// chat gpt code stop
}
