package com.spring.app.conntroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.app.dao.StudentDao;
import com.spring.app.model.Student;

@Controller
public class HomeController {

	@Autowired
	private StudentDao studentDao;
	
	@GetMapping("/")
	public String home() {
		
		return "home";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET )
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("/logout-success")
	public String logout() {
		
		return "logout";
	}
	
	@RequestMapping("/registstu")
	public String registrationPage() {
		
		return "registration";
	}
	
	@PostMapping("/savestu")
	public String addStu(Student s) {
		studentDao.save(s);
		
		return "login";
	}
	
	@GetMapping("/login1")
	public String log() {
		
		return "login";
	}
	
	
}
