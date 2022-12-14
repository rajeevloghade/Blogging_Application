package com.blog.wrapper;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "User Wrapper")
public class UserWrapper {

	private int userId;

	@NotEmpty
	@Size(min = 4, message = "must not be less than four character")
	private String name;

	@Email(message = "email address is invalid")
	private String email;

//	@JsonIgnore
	@NotEmpty
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "a digit must occur at least once and a lower case letter must occur at least once and an upper case letter must occur at least once and a special character must occur at least once and no whitespace allowed in the entire string and anything, at least eight places though in paasword")
	private String password;

	@NotEmpty
	private String about;

	private Set<UserRoleWrapper> roles = new HashSet<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "UserWrapper [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", about=" + about + ", roles=" + roles + "]";
	}

}
