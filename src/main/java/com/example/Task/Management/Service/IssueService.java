package com.example.Task.Management.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task.Management.Client.UserClient;
import com.example.Task.Management.DTO.IssueDto;
import com.example.Task.Management.Entity.Issue;
import com.example.Task.Management.Entity.IssueComments;
import com.example.Task.Management.Entity.Lable;
import com.example.Task.Management.Entity.Sprint;
import com.example.Task.Management.Enum.IssueStatus;
import com.example.Task.Management.Enum.IssueType;
import com.example.Task.Management.Enum.Role;
import com.example.Task.Management.Repository.IssueCommentRepository;
import com.example.Task.Management.Repository.IssueRepository;
import com.example.Task.Management.Repository.LableRepository;
import com.example.Task.Management.Repository.SprintRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueService {
	
	@Autowired
	private IssueRepository issueRepo;
	
	@Autowired
	private LableRepository lableRepo;
	
	@Autowired
	private SprintRepository sprintRepo;
	
	@Autowired
	private IssueCommentRepository issueCommentRepository;
	
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private UserClient userClient;
	
	public IssueService(IssueRepository issueRepo,LableRepository lableRepo,
			SprintRepository sprintRepo,IssueCommentRepository issueCommentRepository) {
		this.issueRepo=issueRepo;
		this.lableRepo=lableRepo;
		this.sprintRepo=sprintRepo;
		this.issueCommentRepository=issueCommentRepository;
	}
	
	private String generateKey(Long id) {
		return "PROJ-"+id;
	}
	
	@Transactional
	public IssueDto createIssue(IssueDto dto) {
		
		Issue issue = new Issue();
		issue.setIssueTitle(dto.getIssueTitle());
		issue.setIssueDescription(dto.getIssueDescription());
		issue.setIssueType(dto.getIssueType());
		issue.setIssuePriority(dto.getIssuePriority());
		issue.setIssueStatus(dto.getIssueStatus());
		issue.setAssignedEmail(dto.getAssignedEmail());
		issue.setReporterEmail(dto.getReporterEmail());
		issue.setDueDate(dto.getDueDate());
		
		if(dto.getLables() !=null ) {
			for(String lableName : dto.getLables()) {
				Lable  lable = lableRepo.findByLablename(lableName).orElse(null);
				if(lable == null) {
					lable =new Lable(lableName);
					lableRepo.save(lable);
				}
				issue.getLables().add(lable);
			}
		}
		issueRepo.save(issue);
		issue.setIssueKey(generateKey(issue.getId()));
		issueRepo.save(issue);
		return toDTO(issue);
		
	}
	
	public IssueDto getById(Long id) {
		Issue issue = issueRepo.findById(id).orElseThrow(()-> new RuntimeException("Issue not found"));
		
		return toDTO(issue);
	}
	
	public List<IssueDto>getByAssignedEmail(String assignedEmail){
		return issueRepo.findByAssignedEmail(assignedEmail).stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public List<IssueDto>getBySprint(Long sprintId){
		return issueRepo.findBySprintId(sprintId).stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public List<IssueDto>getByEpicId(Long epicId){
		return issueRepo.findByEpicId(epicId).stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	@Transactional
	public IssueComments addComment(Long issueId,String autherEmail,String body) {
		Issue issue = issueRepo.findById(issueId).orElseThrow(()-> new RuntimeException("Issue not Found"));
		IssueComments comment = new IssueComments();
		comment.setIssueId(issueId);
		comment.setAutherEmail(autherEmail);
		comment.setBody(body);
		
		issueCommentRepository.save(comment);
		return comment;
	}
	
	@Transactional
	public IssueDto updateStatus(Long id,IssueStatus toStatus,String userOfficialEmail) {
		Issue issue = issueRepo.findById(id).orElseThrow(()-> new RuntimeException("Issue not Found"));
		IssueStatus newStatus = IssueStatus.valueOf(userOfficialEmail);
		
		issue.setIssueStatus(newStatus);
		issue.setUpdateAt(LocalDateTime.now());
		IssueStatus fromStatus = issue.getIssueStatus();
		Long workFlowId = issue.getWorkFlowId();
		if(workFlowId==null) {
			throw new  RuntimeException("workflow not assigned to issue");
		}
		Set<Role>userRole=userClient.getRole(userOfficialEmail);
		workFlowService.isTransactionAllowed(workFlowId, fromStatus, fromStatus,userRole);
		issue.setIssueStatus(toStatus);
		
		issueRepo.save(issue);
		addComment(id,userOfficialEmail,"Status changed from"+fromStatus+"->"+toStatus);
		return toDTO(issue);
	}
	
	@Transactional
	public Sprint createSprint(Sprint sprint) {
		return sprintRepo.save(sprint);
		
	}
	// search section
	public List<IssueDto>search(Map<String,String> filters){
		if(filters.containsKey("assignee")) return getByAssignedEmail(filters.get("assignee"));
		if(filters.containsKey("sprint")) {
			Long sprintId = Long.valueOf(filters.get("sprint"));
			
			return getBySprint(sprintId);
		}
		
		if(filters.containsKey("status")) {
			IssueStatus status = IssueStatus.valueOf(filters.get("status").toUpperCase());
			return issueRepo.findByIssueStatus(status).stream().map(this::toDTO).collect(Collectors.toList());
		}
		return issueRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	private IssueDto toDTO(Issue issue) {
		
		IssueDto dto = new IssueDto();
		
		dto.setIssueTitle(issue.getIssueTitle());
		dto.setIssueKey(issue.getIssueKey());
		dto.setIssueDescription(issue.getIssueDescription());
		dto.setIssueType(issue.getIssueType());
		dto.setIssuePriority(issue.getIssuePriority());
		dto.setIssueStatus(issue.getIssueStatus());
		dto.setAssignedEmail(issue.getAssignedEmail());
		dto.setReporterEmail(issue.getReporterEmail());
		dto.setEpicId(issue.getEpicId());
		dto.setSprintId(issue.getSprintId());
		dto.setCreatedAt(issue.getCreatedAt());
		dto.setDueDate(issue.getDueDate());
		dto.setUpdateAt(issue.getUpdateAt());
		dto.setLables(issue.getLables().stream().map(Lable::getLableName).collect(Collectors.toSet()));
		
		return dto;
		
	}
}
