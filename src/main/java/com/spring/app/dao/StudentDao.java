package com.spring.app.dao;

import com.spring.app.model.Student;

public interface StudentDao {

	public int save(Student s);
	public Student findByUsername(String username);
}
