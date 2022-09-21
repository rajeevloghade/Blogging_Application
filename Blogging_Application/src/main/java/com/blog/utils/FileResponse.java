package com.blog.utils;

import org.springframework.http.HttpStatus;

public class FileResponse {

	private String fileName;
	private String message;
	private Boolean status;
	private HttpStatus code;
	private Object payload;

	public FileResponse() {
		super();
	}

	public FileResponse(String fileName, String message, Boolean status, HttpStatus code, Object payload) {
		super();
		this.fileName = fileName;
		this.message = message;
		this.status = status;
		this.code = code;
		this.payload = payload;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FileResponse [fileName=" + fileName + ", message=" + message + ", status=" + status + ", code=" + code
				+ ", payload=" + payload + "]";
	}

}
