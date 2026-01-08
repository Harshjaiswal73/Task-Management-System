package com.example.Task.Management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Task.Management.Entity.Attachment;
import com.example.Task.Management.Service.AttachmentService;

@RestController
@RequestMapping("/api/file_upload")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;
	
	@PostMapping("upload")
	public ResponseEntity<Attachment>upload(@RequestParam Long issueId,
											@RequestParam MultipartFile file,
											@RequestParam String uploadedBy){
		Attachment saved = attachmentService.upload(issueId, file, uploadedBy);
		return ResponseEntity.ok(saved);
	}
	
	@GetMapping("/{id}/download")
	public ResponseEntity<byte[]>download(@PathVariable Long id){
		byte[] data = attachmentService.download(id);
		String fileName = attachmentService.getDownloadByFileName(id);
		String contentType = attachmentService.getDownloadbyContentType(id);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment:fileName=\""+fileName+"\"")
				.contentType(MediaType.parseMediaType(contentType)).body(data);
	}
	
}
