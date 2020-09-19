package com.revature.controllers;

import java.util.List;
import java.util.Optional;

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

import com.revature.models.Recipe;
import com.revature.repositories.IRecipeDAO;

@RestController
@RequestMapping(value = "/recipe")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RecipeController {
	
	private IRecipeDAO rDao;

	@Autowired
	public RecipeController(IRecipeDAO rDao) {
		super();
		this.rDao = rDao;
	}

	@GetMapping
	public ResponseEntity<List<Recipe>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(rDao.findAll());
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
		if(r!=null)
		{
			rDao.save(r);
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
