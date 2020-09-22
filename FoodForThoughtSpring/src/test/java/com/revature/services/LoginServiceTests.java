//still doesn't work, can't figure it out

package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.revature.models.User;
import com.revature.repositories.IUserDAO;
@ContextConfiguration
@DataJpaTest
class LoginServiceTests {

	@MockBean
	public static IUserDAO uDao;
	@Autowired
	public static LoginService ls;
	public static User u;
	
	@BeforeAll
	static void setUp(){
		u = new User();
		u.setId(1);
		u.setUsername("test");
		u.setPassword("test");
		Mockito.when(uDao.findByUsername("test"))
	      .thenReturn(u);
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
