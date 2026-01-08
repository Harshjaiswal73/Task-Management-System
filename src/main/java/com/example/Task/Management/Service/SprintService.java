package com.example.Task.Management.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task.Management.Entity.Issue;
import com.example.Task.Management.Entity.Sprint;
import com.example.Task.Management.Enum.IssueStatus;
import com.example.Task.Management.Enum.SprintState;
import com.example.Task.Management.Repository.IssueRepository;
import com.example.Task.Management.Repository.SprintRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SprintService {

	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private IssueRepository issueRepository;
	
	public Sprint createSprint(Sprint sprint) {
		
		sprint.setSprintState(SprintState.PLANNED);
		return sprintRepository.save(sprint);
	}
	
	@Transactional	
	public Issue assignIssueToSprint(Long sprintId,Long issueId) {
		
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not Found"));
		
		Issue issue = issueRepository.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		if(sprint.getSprintState()==SprintState.COMPLETED) {
			throw new RuntimeException("Can not add task to complete sprint");
		}
		
		issue.setSprintId(sprintId);
		issueRepository.save(issue);
		
		return issue;
		
	}
	
	@Transactional
	public Sprint startSprint(Long sprintId) {
		
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		if(sprint.getSprintState()==SprintState.PLANNED) {
			throw new RuntimeException("only PLANNED sprint can be start");
		}
		sprint.setSprintState(SprintState.ACTIVE);
		if(sprint.getStartDate()==null) {
			sprint.setStartDate(LocalDateTime.now());
		}
		
		return sprintRepository.save(sprint);
		
	}
	
	@Transactional
	public Sprint closeSprint(Long sprintId) {
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		
		sprint.setSprintState(SprintState.COMPLETED);
		
		if(sprint.getEndDate()==null) {
			sprint.setEndDate(LocalDateTime.now());
		}
		
		List<Issue> issues = issueRepository.findBySprintId(sprintId);
		
		for(Issue issue:issues) {
			if(!issue.getIssueStatus().name().equals(IssueStatus.DONE)) {
				issue.setSprintId(null);
				issue.setBackLogPosition(0);
				issueRepository.save(issue);
			}
		}
		return sprintRepository.save(sprint);
	}
	
	public Map<String, Object>getBurnDownDate(Long sprintId){
		Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new RuntimeException("Sprint not found"));
		LocalDateTime start = sprint.getStartDate();
		LocalDateTime end = sprint.getEndDate()!=null?sprint.getEndDate():LocalDateTime.now();
		
		List<Issue> issues = issueRepository.findBySprintId(sprintId);
		
		int totalTasks = issues.size();
		
		Map<String, Object> burnDown = new LinkedHashMap<>();
		
		LocalDateTime cursor = start;
		
		while(!cursor.isAfter(end)) {
			long completed = issues.stream().filter(i -> i.getIssueStatus().name().equals(IssueStatus.DONE)).count();
			burnDown.put(cursor.toString(),totalTasks-(int) completed);
			cursor=cursor.plusDays(1);
		}
		Map<String, Object>response = new HashMap<>();
		response.put("sprintId",sprintId);
		response.put("startDate",start);
		response.put("endDate",end);
		response.put("burnDown",burnDown);
		
		return response;
	}
	
}
