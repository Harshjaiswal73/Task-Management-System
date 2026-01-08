package com.example.Task.Management.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.Issue;
import com.example.Task.Management.Enum.IssueStatus;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {
	
	Optional<Issue>findByIssueKey(String issueKey);
	List<Issue>findByAssignedEmail(String assignedEmail);
	List<Issue>findBySprintId(Long sprintId);
	List<Issue>findByEpicId(Long epicId);
	List<Issue>findByIssueStatus(IssueStatus status);
	Optional<Issue>findById(Long id);
	
	//List<Issue>findByProjectIdAndSprintIdOrderByBacklogPosition(Long projectId);
	List<Issue> findBySprintIdOrderByBackLogPosition(Long sprintId);

}
