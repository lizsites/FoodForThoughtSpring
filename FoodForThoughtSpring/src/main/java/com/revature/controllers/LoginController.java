package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.services.LoginService;

@RestController
@CrossOrigin(value="*", allowedHeaders="*")
@ResponseBody
public class LoginController {
	private static Logger log = LogManager.getLogger(LoginController.class);
	private LoginService ls;
	
	//inject login service
	private HttpSession sesh;
	@Autowired
	public LoginController(LoginService ls, HttpSession sesh) {
		super();
		this.ls = ls;
		this.sesh = sesh;
	}
	
	@PostMapping(path="/login")
	public ResponseEntity<User> login(@RequestBody User u) {	
		System.out.println("User u being passed:" + u);
		if (ls.login(u)) {
			User f = ls.findUser(u.getUsername());
			System.out.println("User f being set on session:" + f);
			sesh.setAttribute("user", f);
			System.out.println("Current User: " + sesh.getAttribute("user"));
			sesh.setAttribute("loggedin" , true);
			log.info("User " + f.getUsername() + " logged in");
			return ResponseEntity.status(HttpStatus.OK).body(f);
		}else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
}
	
	@GetMapping(path="/logout")
	public ResponseEntity<HttpStatus> logout() {
		//invalidate session and return successful if logged in
		//if (sesh != null && (boolean)sesh.getAttribute("loggedin")) {
			System.out.println("Current User before logout: " + sesh.getAttribute("user"));
			sesh.invalidate();
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		//} else {
			//return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		//}
	}
	
	@PutMapping(path="/updateInfo")
	public ResponseEntity<User> updateUser(@RequestBody User u){
		//update both the session-stored user info and the database entry
		if (sesh != null && (boolean)sesh.getAttribute("loggedin")) {
			//System.out.println("User u (being used to fetch stuff): " + u);
			User f = ls.findUser(u.getUsername());
			System.out.println("user before update: " + f );
			f.setPassword(u.getPassword());
			f.setPreference(u.getPreference());
			if (ls.saveUser(f)!=null) {
				//log.info("User updated: "+ f);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(f);
			} else
				//return current user without updating if failed
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u);
		}else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}
	
	@PostMapping(path="/register")
	public ResponseEntity<User> addUser(@RequestBody User u){

		if (ls.saveUser(u)!=null) {
			
			sesh.setAttribute("user", u);
			sesh.setAttribute("loggedin" , true);
			//log.info("User created: " + u);
			//send back the user if successful
			return ResponseEntity.status(HttpStatus.CREATED).body(u);
		}else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	


}
