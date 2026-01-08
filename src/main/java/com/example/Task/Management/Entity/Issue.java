package com.example.Task.Management.Entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.example.Task.Management.Enum.IssuePriority;
import com.example.Task.Management.Enum.IssueStatus;
import com.example.Task.Management.Enum.IssueType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false,unique =true)
	private String issueKey;
	
	@Column(nullable = false)
	private String issueTitle;
	
	@Column(length=2000)
	private String issueDescription;
	
	@Enumerated(EnumType.STRING)
	private IssueType issueType;
	
	@Enumerated(EnumType.STRING)
	private IssuePriority issuePriority;
	
	@Enumerated(EnumType.STRING)
	private IssueStatus issueStatus;
	
	
	private Long epicId;
	private Long sprintId;
	private String assignedEmail;
	private String reporterEmail;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updateAt = LocalDateTime.now();
	private LocalDateTime dueDate;
	private Long workFlowId;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="issue_lable",
	joinColumns = @JoinColumn(name="issue_id"),inverseJoinColumns=@JoinColumn(name="lable_id"))
	private Set<Lable> lables = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name="source_issue_id",nullable = false)
	private Issue sourceIssue;
	
	@ManyToOne
	@JoinColumn(name="target_issue_id",nullable =false)
	private Issue targetIssueId;
	
	private Integer backLogPosition;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Long getEpicId() {
		return epicId;
	}

	public void setEpicId(Long epicId) {
		this.epicId = epicId;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
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

	public Set<Lable> getLables() {
		return lables;
	}

	public void setLables(Set<Lable> lables) {
		this.lables = lables;
	}


	public Long getWorkFlowId() {
		return workFlowId;
	}

	public void setWorkFlowId(Long workFlowId) {
		this.workFlowId = workFlowId;
	}

	public Issue getSourceIssue() {
		return sourceIssue;
	}

	public void setSourceIssue(Issue sourceIssue) {
		this.sourceIssue = sourceIssue;
	}

	public Issue getTargetIssueId() {
		return targetIssueId;
	}

	public void setTargetIssueId(Issue targetIssueId) {
		this.targetIssueId = targetIssueId;
	}
	public Integer getBackLogPosition() {
		return backLogPosition;
	}

	public void setBackLogPosition(Integer backLogPosition) {
		this.backLogPosition = backLogPosition;
	}
}
