package com.example.firstspringdata.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstspringdata.entity.Student;
import com.example.firstspringdata.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	StudentRepository repository;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public List<Student> listStudent() {
		return this.repository.findAll();
	}
	
	@RequestMapping(value = "/student", method = RequestMethod.POST)
	public Student saveStudent(@RequestBody Student entity) {
		return this.repository.save(entity);
	}
	
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public Optional<Student> findById(@PathVariable String id) {
		return this.repository.findById(id);
	}
	
	@RequestMapping(value = "/student/{name}/name", method = RequestMethod.GET)
	public List<Student> findByNameLikeIgnoreCase(@PathVariable String name) {
		return this.repository.findByNameLikeIgnoreCase(name);
	}
	
	

}
