package com.example.helpdesk.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.helpdesk.api.entity.User;
import com.example.helpdesk.api.repository.UserRepository;
import com.example.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;

	@Override
	public User findByEmail(String email) {
		return this.repository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return this.repository.save(user);
	}

	@Override
	public User findById(String id) {
		return this.repository.findById(id).get();
	}

	@Override
	public void delete(String id) {
		this.repository.deleteById(id);
	}

	@Override
	public Page<User> findAll(int page, int count) {
		return this.repository.findAll(PageRequest.of(page, count));
	}

}
