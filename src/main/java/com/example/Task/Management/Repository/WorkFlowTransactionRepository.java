package com.example.Task.Management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Task.Management.Entity.WorkFlowTransaction;
import com.example.Task.Management.Enum.IssueStatus;

@Repository
public interface WorkFlowTransactionRepository extends JpaRepository<WorkFlowTransaction, Long> {

	Optional<WorkFlowTransaction>findByWorkFlowIdAndFromStatusandToStatus(Long workFlowId,IssueStatus fromStatus,IssueStatus toStatus);
	
}
