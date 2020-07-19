package com.backend.hatukfinalproject.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	
	private String uploadPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\";
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		Date date = new Date();
		
		String fileExtension = file.getContentType().split("/")[1];
		String fileName = "PROFPIC-" + date.getTime() + "." + fileExtension;
		
		System.out.println(fileExtension);
		
		String newFileName = StringUtils.cleanPath(fileName);
		Path path = Paths.get(StringUtils.cleanPath(uploadPath) + newFileName);
		
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documents/download/").path(newFileName).toUriString();
		return fileDownloadUri;
	}
	
	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Object> downloadFile(@PathVariable String fileName) {
		Path path = Paths.get(uploadPath + fileName);
		Resource resource = null;
		
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream")).header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + resource.getFilename() + "\"").body(resource);
	}
}
