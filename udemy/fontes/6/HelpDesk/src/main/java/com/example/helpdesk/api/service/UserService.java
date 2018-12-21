package com.example.helpdesk.api.service;

import org.springframework.data.domain.Page;

import com.example.helpdesk.api.entity.User;

public interface UserService {

	public User findByEmail(String email);

	public User createOrUpdate(User user);

	public User findById(String id);

	public void delete(String id);

	public Page<User> findAll(int page, int count);

}
