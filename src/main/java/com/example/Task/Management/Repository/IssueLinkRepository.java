package com.example.Task.Management.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.IssueLink;

@Repository
public interface IssueLinkRepository extends JpaRepository<IssueLink, Long> {
	
	List<IssueLink>findBySourceIssueId(Long SourceIssueId);
	List<IssueLink>findByTargetIssueId(Long targetIssueId);
	
}
