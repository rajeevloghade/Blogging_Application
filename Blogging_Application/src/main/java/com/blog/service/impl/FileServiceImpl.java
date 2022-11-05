package com.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.service.IFileService;
import com.blog.utils.FileResponse;
import com.blog.utils.IConstants;

@Service
public class FileServiceImpl implements IFileService {

	private static final Logger LOGGER = LogManager.getLogger(FileServiceImpl.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	@Override
	public FileResponse uploadImage(String path, MultipartFile file) {
		LOGGER.info("Inside uploadFile in FileServiceImpl method started with path: {},file: {}", path, file);
		FileResponse fileResponse = null;

		// File name
		String fileName = file.getOriginalFilename();

		// Generate random file name
		String randomUUID = UUID.randomUUID().toString();
		String randomFileName = randomUUID.concat(fileName.substring(fileName.lastIndexOf(".")));

		// Full path
		String filePath = path + File.separator + randomFileName;

		// Create folder if not exist
		File folder = new File(path);
		if (!folder.exists())
			folder.mkdir();

		// File Copy
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
			fileResponse = new FileResponse(randomFileName, IConstants.FILE_UPLOADED, IConstants.TRUE,
					HttpStatus.CREATED, IConstants.SUCCESS);
		} catch (IOException e) {
			fileResponse = new FileResponse(randomFileName, IConstants.SOMETHING_WENT_WRONG, IConstants.FALSE,
					HttpStatus.INTERNAL_SERVER_ERROR, IConstants.FAILURE);
		}

		return fileResponse;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		LOGGER.info("Inside downloadFile in FileServiceImpl method started with fileName: {},path: {}", fileName, path);
		InputStream inputStream = null;
		// Full path
		String filePath = path + File.separator + fileName;
		inputStream = new FileInputStream(filePath);
		return inputStream;
	}

}
