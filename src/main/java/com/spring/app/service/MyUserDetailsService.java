package com.spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.app.dao.StudentDao;
import com.spring.app.model.Student;
import com.spring.app.model.UserPrincipal;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private StudentDao studentDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Student s=studentDao.findByUsername(username);
		
		if(s==null)
			throw new UsernameNotFoundException("User 404 not found");
		
		return new UserPrincipal(s);
		
	}

}
