package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Ingredient;
import com.revature.models.Recipe;
import com.revature.models.Steps;
import com.revature.models.User;
import com.revature.repositories.IRecipeDAO;
import com.revature.repositories.IStepsDAO;
import com.revature.repositories.IngredientDAO;

@RestController
@RequestMapping(value = "/recipe")
@CrossOrigin(origins="localhost:4200/recipe")
public class RecipeController {
	
	private IRecipeDAO rDao;
	private IStepsDAO stepDao;
	private IngredientDAO ingDao;
	private HttpSession sess;
	@Autowired
	public RecipeController(IRecipeDAO rDao, IStepsDAO stepDao, IngredientDAO ingDao, HttpSession sess) {
		super();
		this.rDao = rDao;
		this.stepDao = stepDao;
		this.ingDao = ingDao;
		this.sess = sess;
	}

	@GetMapping(value="/all")
	public ResponseEntity<List<Recipe>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(rDao.findAll());
	}
	
	@GetMapping
	public ResponseEntity<List<Recipe>> getUserRecipes(@RequestBody User u) {
		return ResponseEntity.status(HttpStatus.OK).body(rDao.findByOwner(u));
	}
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Recipe> getById(@PathVariable("id") int id) {
		Optional<Recipe> o = rDao.findById(id);
		if(o.isPresent())
		{
			Recipe r = o.get();
			return ResponseEntity.status(HttpStatus.OK).body(r);
		}else {		
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe r){
		if(r!=null){
			User u = (User) sess.getAttribute("user");
			System.out.println("Sessioned user right before setting recipe: " + u);
			r.setOwner(u);
			rDao.save(r);
			for (Steps step : r.getRecipeStep()) {
				step.setRecipe(r);
				stepDao.save(step);
			}
			for (Ingredient i : r.getIngredients()) {
				i.setRecipe(r);
				ingDao.save(i);
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(r);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping
	public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe r) {
		if(r!=null)
		{
			rDao.save(r);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(r);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
