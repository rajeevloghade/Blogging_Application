package com.blog.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.security.JWTTokenHelper;
import com.blog.wrapper.JWTAuthenticationRequest;
import com.blog.wrapper.JWTAuthenticationResponse;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

	private static final Logger LOGGER = LogManager.getLogger(AuthenticationController.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired JWTTokenHelper jWTTokenHelper;
	private @Autowired UserDetailsService userDetailsService;
	private @Autowired AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<JWTAuthenticationResponse> createToken(@RequestBody JWTAuthenticationRequest request)
			throws Exception {
		LOGGER.info("Inside createToken method in AuthenticationController started with JWTAuthenticationRequest: {}",
				request);
		authenticate(request.getUserName(), request.getPassword());
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(request.getUserName());
		String token = jWTTokenHelper.generateToken(loadUserByUsername);
		JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
		jwtAuthenticationResponse.setToken(token);
		return new ResponseEntity<JWTAuthenticationResponse>(jwtAuthenticationResponse, HttpStatus.OK);
	}

	private void authenticate(String userName, String password) throws Exception {
		LOGGER.info("Inside authenticate method in AuthenticationController started with userName: {}, password: {}",
				userName, password);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,
				password);
		try {
			authenticationManager.authenticate(authentication);
		} catch (BadCredentialsException badCredentialsException) {
			LOGGER.error(
					"Exception ocuured while authenticating credentials inside authenticate method in AuthenticationController with userName: {}, password: {}, badCredentialsException: {}",
					userName, password, badCredentialsException);
			throw new Exception("Invalid Credentials");
		}
	}

}
