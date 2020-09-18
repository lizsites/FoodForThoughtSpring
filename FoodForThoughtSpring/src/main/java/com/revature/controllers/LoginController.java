package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;

import com.revature.models.User;

import com.revature.services.LoginService;

@RestController
@RequestMapping(path="/login")
@CrossOrigin
public class LoginController {
	
	private LoginService ls;
	//inject login service
	
	@Autowired
	public LoginController(LoginService ls) {
		super();
		this.ls = ls;
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<User> login(User u, HttpSession sesh) {	
		
		if (ls.login(u)) {
			User f = ls.getUser(u.getUsername());
			sesh.setAttribute("user", f);
			sesh.setAttribute("loggedin" , true);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(f);
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
	
	@PostMapping(path="/logout")
	//logout doesn't need to return anything except status because the result is reflected in the session
	public ResponseEntity<HttpStatus> logout(HttpSession sesh) {
		//invalidate session and return successful if logged in
		if (sesh != null && (boolean)sesh.getAttribute("loggedin")) {
			sesh.invalidate();
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
	
	@PutMapping(path="/updateInfo")
	public ResponseEntity<User> updateUser(User u, HttpSession sesh){
		//update both the session-stored user info and the database entry
		if (sesh != null && (boolean)sesh.getAttribute("loggedin")) {
			if (ls.updateUser(u)) {
				User f = ls.getUser(u.getUsername());
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(f);
			} else
				//return current user without updating if failed (don't know when this would happen but can't hurt to handle it)
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	@PostMapping(path="/register")
	public ResponseEntity<User> addUser(User u, HttpSession sesh){

		if (ls.register(u)) {
			sesh.setAttribute("user", u);
			sesh.setAttribute("loggedin" , true);
			//send back the user if successful
			return ResponseEntity.status(HttpStatus.CREATED).body(u);
		}else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}


}
