package com.blog.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.User;

public interface IUserDao extends JpaRepository<User, Integer> {

	List<User> findByNameContaining(String userName);

	Optional<User> findByEmail(String email);

}
