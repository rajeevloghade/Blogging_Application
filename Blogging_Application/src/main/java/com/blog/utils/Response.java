package com.blog.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Response")
public class Response {

	@ApiModelProperty(notes = "Apis' execution status", example = "true")
	private String status;
	@ApiModelProperty(notes = "Message to define the execution status of an APis", example = "success")
	private String message;
	@ApiModelProperty(notes = "Payload which APis returns as a response")
	private Object payload;

	public Response(String status, String message, Object payload) {
		super();
		this.status = status;
		this.message = message;
		this.payload = payload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + ", payload=" + payload + "]";
	}

}
