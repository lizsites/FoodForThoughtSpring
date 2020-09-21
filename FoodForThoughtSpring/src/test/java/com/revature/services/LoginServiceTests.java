package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.User;
import com.revature.repositories.IUserDAO;

class LoginServiceTests {

	public static LoginService ls;
	public static IUserDAO uDao;
	public static User u;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ls = new LoginService(uDao);
		u = new User();
		u.setUsername("Olivia");
		u.setPassword("5B5921AA233DB59A0499718B5E69A165");
	}

	@Test
	void testLogin() {
		System.out.println("Testing login");
		assertTrue(ls.login(u));
		System.out.println("Login test successful");
		
	}
	@Test
	void testFindUser() {
		System.out.println("Testing find by username");
		User f = (ls.findUser(u.getUsername()));
		assertEquals(f.getId(), 1);
		System.out.println("Find user test successful");
	}

}
