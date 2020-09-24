package com.revature.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
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
import com.revature.services.LoginService;
import com.revature.services.RecipeService;

@RestController
@RequestMapping(value = "/recipe")
@CrossOrigin(origins = "localhost:8090/recipe")
public class RecipeController {
	private Logger log = LogManager.getLogger(RecipeController.class);
	private RecipeService rs;
	private LoginService ls;
	private HttpSession sess;

	@Autowired
	public RecipeController(LoginService ls, RecipeService rs, HttpSession sess) {
		super();
		this.ls = ls;
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
	public ResponseEntity<User> saveRecipe(@RequestBody Recipe r) {
		if (r != null) {
			User u = (User) sess.getAttribute("user");
			String username = u.getUsername();
			System.out.println("Sessioned user right before setting recipe: " + u);
			r.setOwner(u);
			
			//saving each step and ingredient to their tables is now done within saveRecipe
			if(username!= null) {			
				rs.saveRecipe(r);
				u = ls.findUser(username);
				log.info("Recipe created: " + r);
			} else {
				log.info("User is "+ u.getUsername() + ": failed to create recipe");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
				
			//if(u.getUsername()!=null && rs.saveRecipe(r)) {
				//log.info("Recipe created: " + r);
			//}else {
				//log.info("User is "+ u.getUsername() + ": failed to create recipe");
			//}			
			return ResponseEntity.status(HttpStatus.CREATED).body(u);
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping
	public ResponseEntity<User> updateRecipe(@RequestBody Recipe r) {
		if (r != null) {
			User u = (User) sess.getAttribute("user");
			String username = u.getUsername();
			rs.saveRecipe(r);
			u = ls.findUser(username);
			log.info("Recipe updated: " + r);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
		} else
		{
			log.info("Failed to update recipe: " + r);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}
