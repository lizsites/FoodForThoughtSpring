package com.revature.services;

//spring boot uses JUnit Jupiter by default instead of JUnit 4. I imported the JUnit 4 dependency to avoid confusion
//if this doesn't work with spring boot then we can switch back to the default, it's just different syntax
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.User;
import com.revature.repositories.IUserDAO;

class LoginServiceTests {

	public static LoginService ls;
	public static IUserDAO uDao;
	public static User u;
	@BeforeClass
	static void setUpBeforeClass() throws Exception {
		u = new User();
		u.setUsername("Olivia");
		u.setPassword("5B5921AA233DB59A0499718B5E69A165");
		ls = new LoginService(uDao);
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
