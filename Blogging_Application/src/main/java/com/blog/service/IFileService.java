package com.blog.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.blog.utils.FileResponse;

public interface IFileService {

	FileResponse uploadImage(String path, MultipartFile file);

	InputStream getResource(String path, String fileName) throws FileNotFoundException;
}
