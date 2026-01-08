package com.example.Task.Management.Entity;

import java.time.LocalDateTime;

import com.example.Task.Management.Enum.SprintState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;



@Entity
@Table(name="sprints")
@Builder
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sprintName;
	
	private LocalDateTime startDate;
	
	private LocalDateTime endDate;
	
	@Enumerated(EnumType.STRING)
	private SprintState sprintState = SprintState.PLANNED;
	
	@Column(length=5000)
	private String goal;
	
	private LocalDateTime createdAt = LocalDateTime.now();

	private SprintState state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public SprintState getSprintState() {
		return sprintState;
	}

	public void setSprintState(SprintState sprintState) {
		this.sprintState = sprintState;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Sprint(Long id, String sprintName, LocalDateTime startDate, LocalDateTime endDate, SprintState state,
			String goal, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.sprintName = sprintName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sprintState = state;
		this.goal = goal;
		this.createdAt = createdAt;
	}
}
