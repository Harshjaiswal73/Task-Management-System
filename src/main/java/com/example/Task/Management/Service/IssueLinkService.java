package com.example.Task.Management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Task.Management.Entity.IssueLink;
import com.example.Task.Management.Repository.IssueLinkRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IssueLinkService {

	@Autowired
	private IssueLinkRepository issueLinkRepository;
	
	public IssueLink createLink(Long issueSourceId,Long issueTargetId,String linkType) {
		
		IssueLink link = new IssueLink();
		link.setSourceIssueId(issueTargetId);
		link.setTargetIssueId(issueTargetId);
		link.setLinkType(linkType);
		return issueLinkRepository.save(link);
		
	}
	
	public List<IssueLink>getLinkBySource(Long issueSourceId){
		
		return issueLinkRepository.findBySourceIssueId(issueSourceId);
		
	}
	
	public List<IssueLink>getLinkByTarget(Long issueTargetId){
		return issueLinkRepository.findByTargetIssueId(issueTargetId);
	}
	
	public void deleteLink(Long id) {
		issueLinkRepository.deleteById(id);
	}
	
}
