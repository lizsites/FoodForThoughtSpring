package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Ingredient;
import com.revature.models.Recipe;
import com.revature.models.Steps;
import com.revature.models.User;
import com.revature.repositories.IRecipeDAO;
import com.revature.repositories.IStepsDAO;
import com.revature.repositories.IngredientDAO;

@Service
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
		//when the recipe is updated, the changes should reflect in the steps and ingredients table
		if (r != null) {
			for (Steps step : r.getRecipeStep()) {
				step.setRecipe(r);
				sDAO.save(step);
			}
			for (Ingredient i : r.getIngredients()) {
				i.setRecipe(r);
				iDAO.save(i);
			}
			rDAO.save(r);
			return true;
		} else
			return false;
	}
}
