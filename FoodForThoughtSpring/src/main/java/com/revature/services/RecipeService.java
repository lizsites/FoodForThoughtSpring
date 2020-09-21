package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.Ingredient;
import com.revature.models.Recipe;
import com.revature.models.Steps;
import com.revature.models.User;
import com.revature.repositories.IRecipeDAO;
import com.revature.repositories.IStepsDAO;
import com.revature.repositories.IngredientDAO;

public class RecipeService {

	// service class for saving/retrieving recipe information
	private IRecipeDAO rDAO;
	private IStepsDAO sDAO;
	private IngredientDAO iDAO;

	@Autowired
	public RecipeService(IRecipeDAO rDAO, IStepsDAO sDAO, IngredientDAO iDAO) {
		super();
		this.rDAO = rDAO;
		this.sDAO = sDAO;
		this.iDAO = iDAO;
	}

	public List<Recipe> findAll() {
		return rDAO.findAll();
	}

	public Optional<Recipe> findById(int id) {
		return rDAO.findById(id);
	}

	public List<Recipe> findAllByUser(User u) {
		return rDAO.findByOwner(u);
	}

	public boolean saveRecipe(Recipe r) {
		if (r != null) {
			rDAO.save(r);
			return true;
		} else
			return false;
	}

	public boolean saveStep(Steps s) {
		if (s != null) {
			sDAO.save(s);
			return true;
		} else
			return false;
	}

	public boolean saveIngredient(Ingredient i) {
		if (i != null) {
			iDAO.save(i);
			return true;
		} else
			return false;
	}
}
