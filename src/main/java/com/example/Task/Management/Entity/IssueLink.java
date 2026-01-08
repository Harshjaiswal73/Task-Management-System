package com.example.Task.Management.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="issueLink")
public class IssueLink {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long sourceIssueId;
	private Long targetIssueId;
	
	private String linkType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSourceIssueId() {
		return sourceIssueId;
	}

	public void setSourceIssueId(Long sourceIssueId) {
		this.sourceIssueId = sourceIssueId;
	}

	public Long getTargetIssueId() {
		return targetIssueId;
	}

	public void setTargetIssueId(Long targetIssueId) {
		this.targetIssueId = targetIssueId;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}
	
}
