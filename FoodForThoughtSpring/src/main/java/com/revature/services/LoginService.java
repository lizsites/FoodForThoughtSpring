package com.revature.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.User;
import com.revature.repositories.IUserDAO;

@Service
public class LoginService {

	private IUserDAO userDAO;

	@Autowired
	public LoginService(IUserDAO userDAO) {
		super();
		this.userDAO = userDAO;
	}

	public boolean login(User u) {
		//check if password is correct
		User f = userDAO.findByUsername(u.getUsername());
		System.out.println("User f " + f);
		System.out.println("User u " + u);
		return (u.getPassword().equals(f.getPassword()));
	}

	public User saveUser(User u) {
		//save/update user info
		if(u!=null)
		{
			System.out.println("Updated user " + u);
			userDAO.save(u);
		}
		return u;
	}

	public User findUser(String username) {
		//find user by username
		return userDAO.findByUsername(username);
	}
}
