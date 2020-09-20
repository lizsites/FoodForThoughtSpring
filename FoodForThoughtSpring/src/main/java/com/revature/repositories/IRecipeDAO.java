package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Recipe;
import com.revature.models.User;

public interface IRecipeDAO extends JpaRepository<Recipe, Integer>{
	List<Recipe> findByOwner(User u);
}
