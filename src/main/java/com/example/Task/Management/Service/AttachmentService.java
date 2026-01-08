package com.example.Task.Management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Repository.AttachmentRepository;

import jakarta.mail.Multipart;
import jakarta.transaction.Transactional;

@Service
public class AttachmentService {

	@Autowired
	private CloudinaryStorageService storageService;
	
	@Autowired
	private AttachmentRepository attachmentRepository;
	
	@Transactional
	public Attachment upload(Long issueId,MultipartFile file,String uploadedBy) {
		
		String url = storageService.store(file,"issue/"+issueId);
		Attachment attachment = new Attachment();
		attachment.setIssueId(issueId);
		attachment.setFilename(file.getOriginalFilename());
		attachment.setContentType(file.getContentType());
		attachment.setFileSize(file.getSize());
		attachment.setStoragePath(url);
		attachment.setUploadedBy(uploadedBy);
		
		
		return attachmentRepository.save(attachment);
	}
	
	public byte[] download(Long id) {
		Attachment att = attachmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Attachment not found:"+id));
		return storageService.read(att.getStoragePath());
	}
	
	public String getDownloadByFileName(Long id) {
		
		return attachmentRepository.findById(id).map(Attachment::getFilename).orElse("file");
	}
	
	public String getDownloadbyContentType(Long id) {
		return attachmentRepository.findById(id).map(Attachment::getContentType).orElse("application/octect-stream");
	}
	
}
