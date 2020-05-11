package com.spring.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.model.Student;
@Component
public class StudentDaoImpl implements StudentDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public int save(Student s) {
		// TODO Auto-generated method stub
		Session ses=sessionFactory.getCurrentSession();
		s.setPassword(bCryptPasswordEncoder.encode(s.getPassword()));
		ses.save(s);
		
		return s.getSid();
	}

	@Transactional
	public Student findByUsername(String username) {
		// TODO Auto-generated method stub
		Session ses=sessionFactory.getCurrentSession();
		Query query=ses.createQuery("from Student where username=:username");
		query.setParameter("username", username);
		
		List<Student> slist=query.getResultList();
		int size=slist.size();
		
		if(size!=0) {
			for(Student s:slist) {
				if(s!=null) {
					return s;
				}else {
					return null;
				}
			}
		}else {
			return null;
		}
		return null;
	}

}
