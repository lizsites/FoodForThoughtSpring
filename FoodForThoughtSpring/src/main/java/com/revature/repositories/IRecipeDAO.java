package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Recipe;

public interface IRecipeDAO extends JpaRepository<Recipe, Integer>{

}
