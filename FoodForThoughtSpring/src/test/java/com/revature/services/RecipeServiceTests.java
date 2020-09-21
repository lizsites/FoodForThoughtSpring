package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.models.Recipe;
import com.revature.repositories.IRecipeDAO;
import com.revature.repositories.IStepsDAO;
import com.revature.repositories.IngredientDAO;



public class RecipeServiceTests {

	public static RecipeService rs;
	public static IRecipeDAO rDAO;
	public static IStepsDAO sDAO;
	public static IngredientDAO iDAO;
	public static Recipe r;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		
		rs = new RecipeService(rDAO, sDAO, iDAO);
		r = new Recipe();
	}

	@Test
	public void testFindById() {
		System.out.println("Testing find recipe by ID");
		assertNotNull(rs.findById(1));
		System.out.println("Find recipe by ID test successful");
	}

	@Test
	public void testFindAll() {
		System.out.println("Testing find all recipes");
		assertNotNull(rs.findAll());
		System.out.println("Find all recipes test successful");
	}
}
