package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LogManager.getLogger(JWTAuthenticationFilter.class);
	private static final Logger EMAIL = LogManager.getLogger("EMAIL");

	private @Autowired UserDetailsService userDetailsService;
	private @Autowired JWTTokenHelper jWTTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		LOGGER.info(
				"Inside doFilterInternal in JWTAuthenticationFilter method started with request: {},response: {},filterChain: {}",
				request, response, filterChain);
		// Get token
		String requestToken = request.getHeader("Authorization");
		LOGGER.info("requestToken : {}", requestToken);

		String usernameFromToken = null;
		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			LOGGER.info("Token : {}", token);
			try {
				usernameFromToken = jWTTokenHelper.getUsernameFromToken(token);
			} catch (IllegalArgumentException illegalArgumentException) {
				LOGGER.error("Unable to get JWT token");
			} catch (ExpiredJwtException expiredJwtException) {
				LOGGER.error("JWT token has expired");
			} catch (MalformedJwtException malformedJwtException) {
				LOGGER.error("Invalid JWT token");
			}

		} else {
			LOGGER.info("JWT token doesn't begin with Bearer.");
		}

		// Validate token

		if (usernameFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			LOGGER.info("Validating JWT token");
			UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(usernameFromToken);
			if (jWTTokenHelper.validateToken(token, loadUserByUsername)) {
				LOGGER.info("JWT token validated");
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						loadUserByUsername, null, loadUserByUsername.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} else {
				LOGGER.info("Invalid JWT token");
			}
		} else {
			LOGGER.info("usernameFromToken is null or context is no null");
		}

		filterChain.doFilter(request, response);
	}

}
