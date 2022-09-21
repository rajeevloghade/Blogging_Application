package com.blog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.IFileService;
import com.blog.utils.FileResponse;

@RestController
@RequestMapping("api/file")
public class FileRestController {

	private @Autowired IFileService fileService;

	private @Value("${project.image}") String path;

	@PostMapping("fileUpload")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image) {
		FileResponse uploadImage = fileService.uploadImage(path, image);
		return new ResponseEntity<FileResponse>(new FileResponse(uploadImage.getFileName(), uploadImage.getMessage(),
				uploadImage.getStatus(), uploadImage.getCode(), uploadImage.getPayload()), HttpStatus.CREATED);
	}

	@GetMapping(path = "getResource/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadFile(@PathVariable("fileName") String fileName, HttpServletResponse response)
			throws IOException {
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(fileService.getResource(path, fileName), response.getOutputStream());
	}
}
