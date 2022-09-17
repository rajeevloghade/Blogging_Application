package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.User;

public interface IUserDao extends JpaRepository<User, Integer> {

}
