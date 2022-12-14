package com.blog.wrapper;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "JWT Authentication Response")
public class JWTAuthenticationResponse {

	private String token;

	public JWTAuthenticationResponse() {
		super();
	}

	public JWTAuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "JWTAuthenticationResponse [token=" + token + "]";
	}

}
