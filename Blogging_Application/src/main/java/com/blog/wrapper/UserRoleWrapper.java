package com.blog.wrapper;

public class UserRoleWrapper {

	private int userId;

	private String name;

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

	@Override
	public String toString() {
		return "UserRoleWrapper [userId=" + userId + ", name=" + name + "]";
	}

}
