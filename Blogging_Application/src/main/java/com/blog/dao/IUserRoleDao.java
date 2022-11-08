package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.UserRole;

public interface IUserRoleDao extends JpaRepository<UserRole, Integer> {

}
