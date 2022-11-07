package com.blog.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final Logger LOGGER = LogManager.getLogger(JWTAuthenticationEntryPoint.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		LOGGER.info(
				"Inside commence in JWTAuthenticationEntryPoint method started with request: {},response: {},authException: {}",
				request, response, authException);
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}

}
