////still doesn't work, can't figure it out
//
//package com.revature.services;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doReturn;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.revature.FoodForThoughtSpringApplication;
//import com.revature.controllers.LoginController;
//import com.revature.controllers.RecipeController;
//import com.revature.models.Picture;
//import com.revature.models.Preferences;
//import com.revature.models.Preferences.DietType;
//import com.revature.models.Recipe;
//import com.revature.models.User;
//import com.revature.repositories.IPicturesDAO;
//import com.revature.repositories.IRecipeDAO;
//import com.revature.repositories.IStepsDAO;
//import com.revature.repositories.IUserDAO;
//import com.revature.repositories.IngredientDAO;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//@RunWith(FoodForThoughtSpringApplication.class)
////@RunWith(JUnitPlatform.class)
//class LoginServiceTests {
//
//	
//
//	public static User u;
//	public static List<User> users;
//	
//	//public static TestEntityManager entityManager;
//	
//	@InjectMocks
//	private LoginController lc;
//	
//	@InjectMocks
//	private RecipeController rc;
//	
//	@InjectMocks
//	public static LoginService ls;
//	
//	@InjectMocks
//	public static PictureService ps;
//	
//	
//	@Mock
//	public static IUserDAO userDAO;
//	
//	@Mock
//	public static IRecipeDAO recipeDao;
//	
//	@Mock
//	public static IngredientDAO ingredientDAO;
//	
//	@Mock
//	public static IPicturesDAO pictureDAO;
//	
//	@Mock
//	public static IStepsDAO stepDAO;
//	
//	
//	
//	
//	@Autowired(required = true)
//	public LoginServiceTests(LoginService ls, User u, PictureService ps, IUserDAO userDAO, List<User> users, IPicturesDAO pictureDAO) {
//		this.ls = ls;
//		this.u = u;
//		this.ps = ps;
//		this.userDAO = userDAO;
//		this.users = users;
//		this.pictureDAO = pictureDAO;
//		//this.entityManager = entityManager;
//	}
//	
//	@BeforeEach
//	public void init() {
//		MockitoAnnotations.initMocks(this);
////		userDAO = mock(IUserDAO.class);
////		recipeDao = mock(IRecipeDAO.class);
////		ls = mock(LoginService.class);
////		ingredientDAO = mock(IngredientDAO.class);
////		ps = mock(PictureService.class);
//		u = new User();
//		u.setId(90);
//		u.setUsername("test");
//		u.setPassword("test");
//		u.setRecipes(new ArrayList<Recipe>());
//		u.setPictures(new ArrayList<Picture>());
//		u.setPreference(new Preferences(DietType.GLUTEN_FREE,0,0));
//		//System.out.println("u right before being tested" + u);
//		List<User> users = new ArrayList<User>();
//		users.add(u);
//		//System.out.println("user being used " + u);
//		
//	}
//	
////	@BeforeAll
////	static void setUp(){
////		
////	}
//
//	@Test
//	void testLogin() {
//		System.out.println("User at beginning of testLogin() " + u);
//		doReturn(u).when(userDAO).findByUsername(u.getUsername());
//		System.out.println(ls.findUser(u.getUsername()));
//		assertEquals(ls.login(u), true);
//
//		
//	}
//	@Test
//	void testFindUser() {
//		when(userDAO.findByUsername(u.getUsername())).thenReturn(u);
//		when(ls.findUser(u.getUsername())).thenReturn(u);
//		User f = ls.findUser(u.getUsername());
//		assertEquals(f, u);
//	}
//	
//	@Test
//	void testAddPicture() {
//		System.out.println("Test addPicture()");
//		List<Picture> picList = u.getPictures();
//		Picture pic = new Picture();
//		when(pictureDAO.save(pic)).thenReturn(pic);
//		doReturn(u).when(userDAO).findByUsername(u.getUsername());
//		doReturn(picList).when(pictureDAO).findByUser(u);
//		
//		pic.setId(99);
//		pic.setUser(u, true);
//		System.out.println("pic about to be added" +pic);
//		
//		
//		
////		picList.add(pic);
//		ps.savePicture(u, pic);
//		
//		
//		System.out.println("User in testAddPictures " + ls.findUser(u.getUsername()));
//		System.out.println("picture list " + ps.findAllByUser(u.getUsername()));
//		assertTrue(ps.findAllByUser(u.getUsername()).size() > 0);
//	}
//	
//	
//	
//	
//	
//	@AfterAll
//	static void tearDown() {
////		userDAO.delete(u);
//	}
//
//}
