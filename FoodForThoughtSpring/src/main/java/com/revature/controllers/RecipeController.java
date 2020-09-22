package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
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
import com.revature.services.RecipeService;

@RestController
@RequestMapping(value = "/recipe")
@CrossOrigin(origins = "localhost:4200/recipe")
public class RecipeController {
	private Logger log;
	private RecipeService rs;
	private HttpSession sess;

	@Autowired
	public RecipeController(RecipeService rs, HttpSession sess) {
		super();
		this.rs = rs;
		this.sess = sess;
	}

	@GetMapping(value = "/all")
	public ResponseEntity<List<Recipe>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(rs.findAll());
	}

	@GetMapping
	public ResponseEntity<List<Recipe>> getUserRecipes(@RequestBody User u) {
		return ResponseEntity.status(HttpStatus.OK).body(rs.findAllByUser(u));
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Recipe> getById(@PathVariable("id") int id) {
		Optional<Recipe> o = rs.findById(id);
		if (o.isPresent()) {
			Recipe r = o.get();
			return ResponseEntity.status(HttpStatus.OK).body(r);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping
	public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe r) {
		if (r != null) {
			User u = (User) sess.getAttribute("user");
			System.out.println("Sessioned user right before setting recipe: " + u);
			r.setOwner(u);
			rs.saveRecipe(r);
			for (Steps step : r.getRecipeStep()) {
				step.setRecipe(r);
				rs.saveStep(step);
			}
			for (Ingredient i : r.getIngredients()) {
				i.setRecipe(r);
				rs.saveIngredient(i);
			}
			log.info("Recipe created: " + r);
			return ResponseEntity.status(HttpStatus.CREATED).body(r);
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping
	public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe r) {
		if (r != null) {
			rs.saveRecipe(r);
			log.info("Recipe updated: " + r);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(r);
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
