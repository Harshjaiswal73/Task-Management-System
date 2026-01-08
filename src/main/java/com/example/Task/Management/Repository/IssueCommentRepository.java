package com.example.Task.Management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.IssueComments;
@Repository
public interface IssueCommentRepository extends JpaRepository<IssueComments, Long> {

	List<IssueComments>findByIssueOrderByCreatedAt(Long issueId);
}
