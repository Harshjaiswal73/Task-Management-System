package com.example.Task.Management.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task.Management.Entity.WorkFlow;
import com.example.Task.Management.Entity.WorkFlowTransaction;
import com.example.Task.Management.Enum.IssueStatus;
import com.example.Task.Management.Enum.Role;
import com.example.Task.Management.Repository.WorkFlowTransactionRepository;
import com.example.Task.Management.Repository.WorkflowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WorkFlowService {

	@Autowired
	private WorkflowRepository workflowRepository;
	
	@Autowired
	private WorkFlowTransactionRepository workFlowTransactionRepository;
	
	public WorkFlow createworkFlow(String name,List<WorkFlowTransaction> transactions) {
		
		WorkFlow wf = new WorkFlow();
		wf.setName(name);
		for(WorkFlowTransaction t:transactions) {
			t.setWorkflow(wf);
		}
		wf.setTransactions(transactions);
		return workflowRepository.save(wf);
		
	}
	
	public WorkFlow getWorkflow(String name) {
		return workflowRepository.findByName(name).orElseThrow(()-> new RuntimeException("Workflow not found"));
	}
	
	public List<WorkFlow>getAllWorkFlows(){
		return workflowRepository.findAll();
	}
	
	public boolean isTransactionAllowed(Long workFlowId,IssueStatus fromStatus,IssueStatus toStatus,Set<Role>userRole) {
		WorkFlowTransaction wft = workFlowTransactionRepository.findByWorkFlowIdAndFromStatusandToStatus(workFlowId, fromStatus, toStatus).orElseThrow(()-> new RuntimeException("Transaction not defined:"+fromStatus+"->"+toStatus));
		
		boolean allowed = userRole.stream().anyMatch(wft.getRole()::contains);
		
		if(!allowed) {
			throw new RuntimeException("user roles"+ userRole+"not allowed Fortransaction"+fromStatus+"->"+toStatus);
		}
		return true;
	}
	

	
	
}
