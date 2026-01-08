package com.example.Task.Management.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.Task.Management.Enum.IssuePriority;
import com.example.Task.Management.Enum.IssueStatus;
import com.example.Task.Management.Enum.IssueType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueDto {

	
	public String getIssueKey() {
		return issueKey;
	}
	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}
	public String getIssueTitle() {
		return issueTitle;
	}
	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
	public IssueType getIssueType() {
		return issueType;
	}
	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}
	public IssuePriority getIssuePriority() {
		return issuePriority;
	}
	public void setIssuePriority(IssuePriority issuePriority) {
		this.issuePriority = issuePriority;
	}
	public IssueStatus getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(IssueStatus issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getAssignedEmail() {
		return assignedEmail;
	}
	public void setAssignedEmail(String assignedEmail) {
		this.assignedEmail = assignedEmail;
	}
	public String getReporterEmail() {
		return reporterEmail;
	}
	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}
	public long getEpicId() {
		return epicId;
	}
	public void setEpicId(long epicId) {
		this.epicId = epicId;
	}
	public long getSprintId() {
		return sprintId;
	}
	public void setSprintId(long sprintId) {
		this.sprintId = sprintId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
	public LocalDateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	private String issueKey;
	
	
	private String issueTitle;
	
	
	private String issueDescription;
	
	
	private IssueType issueType;
	

	private IssuePriority issuePriority;
	
	
	private IssueStatus issueStatus;
	private String assignedEmail;
	private String reporterEmail;
	
	

	private long epicId;
	private long sprintId;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updateAt = LocalDateTime.now();
	private LocalDateTime dueDate;
	private Set<String> lables;
	public Set<String> getLables() {
		return lables;
	}
	public void setLables(Set<String> lables) {
		this.lables = lables;
	}
}
