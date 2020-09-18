//package com.revature.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.revature.models.Recipe;
//import com.revature.repositories.IRecipeDAO;
//import com.revature.repositories.IUserDAO;
//
//@RestController
//@RequestMapping(value="/recipe")
//@CrossOrigin
//public class RecipeController {
//
//	private IUserDAO uDao;
//	private IRecipeDAO rDao;
//	
//	@Autowired
//	public RecipeController(IUserDAO uDao, IRecipeDAO rDao) {
//		super();
//		this.uDao = uDao;
//		this.rDao = rDao;
//	}
//	
//	@GetMapping
//	public ResponseEntity<List<Recipe>> getAll() {
//		return ResponseEntity.status(HttpStatus.OK).body(rDao.findAll());
//	}
//	
////	@GetMapping(value="/{id}")
////	public ResponseEntity<Trainer> getTrainer(@PathVariable("id") int id){
////		Optional<Trainer> o = tDao.findById(id);
////		if(o.isPresent()) {
////			Trainer t = o.get();
////			return ResponseEntity.status(HttpStatus.OK).body(t);
////		} else {
////			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
////		}
////	}
////	
////	@GetMapping(value="/trainer/{name}")
////	public ResponseEntity<Trainer> getTrainerByName(@PathVariable("name") String name){
////		Trainer t = tDao.findByName(name);
////		if(t==null) {
////			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
////		} else {
////			return ResponseEntity.status(HttpStatus.OK).body(t);
////		}	
////	}
////	
////	@PostMapping
////	public ResponseEntity<List<Trainer>> newTrainer(@RequestBody Trainer t){
////		tDao.save(t);
////		return ResponseEntity.status(HttpStatus.OK).body(tDao.findAll());
////	}
//	
//	
//	@PutMapping
//	public  ResponseEntity<List<Trainer>> newPokemon(@RequestBody Pokemon p){
//		pDao.save(p);
//		return ResponseEntity.status(HttpStatus.OK).body(tDao.findAll());
//	}
//	
//}
