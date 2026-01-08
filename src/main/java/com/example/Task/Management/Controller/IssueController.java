package com.example.Task.Management.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Task.Management.DTO.IssueDto;
import com.example.Task.Management.Entity.IssueComments;
import com.example.Task.Management.Enum.IssueStatus;
import com.example.Task.Management.Service.IssueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {

	@Autowired
	private IssueService issueService;
	
	@PostMapping("/create")
	public ResponseEntity<IssueDto> create(@RequestBody IssueDto dto){
		IssueDto created = issueService.createIssue(dto);
		return ResponseEntity.ok(created);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IssueDto>getByIssueId(@PathVariable Long id){
		return ResponseEntity.ok(issueService.getById(id));
	}
	
	@GetMapping("/assignee/{assignedEmail}")
	public ResponseEntity<List<IssueDto>>getByAssignEmail(@PathVariable String assignedEmail){
		return ResponseEntity.ok(issueService.getByAssignedEmail(assignedEmail));
	}
	
	@PatchMapping("/{id}/status")
	public ResponseEntity<IssueDto>updateStatus(@PathVariable Long id,@RequestParam IssueStatus issueStatus,@RequestHeader(value= "X-User_Email",required=false)String user){
		return ResponseEntity.ok(issueService.updateStatus(id, issueStatus, user));
	}
	
	@PostMapping("/{id}/comments")
	public ResponseEntity<IssueComments>addComments(@PathVariable Long id,@RequestBody Map<String,String>body,@RequestHeader(value= "X-User_Email",required=false)String user){
		
		String commentBody = body.get("body");
		String author = user==null? body.getOrDefault("autherEmail","user@gmail"):user;
		
		return ResponseEntity.ok(issueService.addComment(id, author, commentBody));
		
		
	}
	
}
