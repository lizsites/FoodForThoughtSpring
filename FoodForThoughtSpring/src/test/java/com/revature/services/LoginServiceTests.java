//still doesn't work, can't figure it out

package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.revature.FoodForThoughtSpringApplication;
import com.revature.controllers.LoginController;
import com.revature.controllers.RecipeController;
import com.revature.models.Picture;
import com.revature.models.Preferences;
import com.revature.models.Preferences.DietType;
import com.revature.models.Recipe;
import com.revature.models.User;
import com.revature.repositories.IRecipeDAO;
import com.revature.repositories.IUserDAO;
import com.revature.repositories.IngredientDAO;

@RunWith(FoodForThoughtSpringApplication.class)
@SpringBootTest
class LoginServiceTests {

	
	public static LoginService ls;
	public static User u;
	public static PictureService ps;
	//public static TestEntityManager entityManager;
	
	@InjectMocks
	private LoginController lc;
	
	@InjectMocks
	private RecipeController rc;
	
	@Mock
	public static IUserDAO userDAO;
	
	@Mock
	public static IRecipeDAO recipeDao;
	
	@Mock
	public static IngredientDAO ingredientDAO;
	
	
	@Autowired(required = true)
	public LoginServiceTests(LoginService ls, User u, PictureService ps, IUserDAO userDAO) {
		this.ls = ls;
		this.u = u;
		this.ps = ps;
		this.userDAO = userDAO;
		//this.entityManager = entityManager;
	}
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
//	@BeforeAll
//	static void setUp(){
//		
//	}

	@Test
	void testLogin() {
		u = new User();
		u.setUsername("test");
		u.setPassword("test");
		u.setRecipes(new ArrayList<Recipe>());
		u.setPictures(new ArrayList<Picture>());
		u.setPreference(new Preferences(DietType.GLUTEN_FREE,0,0));
		System.out.println("u right before being tested" + u);
		List<User> users = new ArrayList<User>();
		users.add(u);
		IUserDAO userD = mock(IUserDAO.class);
		when(userD.findAll()).thenReturn(users);
		u.setId(ls.findUser(u.getUsername()).getId());
		System.out.println("Testing login");
		assertTrue(ls.login(u));
		System.out.println("Login test successful");
		
	}
	@Test
	void testFindUser() {
		System.out.println("Testing find by username");
		User f = (ls.findUser(u.getUsername()));
		assertEquals(f.getId(), u.getId());
		System.out.println("Find user test successful");
	}
	
	@Test
	void testAddPicture() {
		System.out.println("Test addPicture()");
		Picture pic = new Picture();
		ps.savePicture(u, pic);
		assertTrue(ls.findUser(u.getUsername()).getPictures().size() > 0);
	}
	
	
	@AfterAll
	static void tearDown() {
		userDAO.delete(u);
	}

}
