package com.example.Task.Management.Entity;

import java.util.Set;

import com.example.Task.Management.Enum.IssueStatus;
import com.example.Task.Management.Enum.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="workFlow_Transactions")
public class WorkFlowTransaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private IssueStatus fromStatus;
	private IssueStatus toStatus;
	
	private String actionName;
	private Set<Role> role;
	
	@ManyToOne
	@JoinColumn(name="workFlow_id")
	private WorkFlow workflow;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public IssueStatus getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(IssueStatus fromStatus) {
		this.fromStatus = fromStatus;
	}

	public IssueStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(IssueStatus toStatus) {
		this.toStatus = toStatus;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public WorkFlow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(WorkFlow workflow) {
		this.workflow = workflow;
	}
	
}
