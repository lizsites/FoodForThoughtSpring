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
		User f = userDAO.findByUsername(u.getUsername());
		System.out.println("User f " + f);
		System.out.println("User u " + u);
		return (u.getPassword().equals(f.getPassword()));
	}

	public User saveUser(User u) {
		return userDAO.save(u);
	}

	public User findUser(String username) {
		return userDAO.findByUsername(username);
	}

	// can this be deleted?
	private static String hash(String pass) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");

			byte[] hashByte = md.digest(pass.getBytes(StandardCharsets.UTF_8));
			String myHash = DatatypeConverter.printHexBinary(hashByte);
			return (myHash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
