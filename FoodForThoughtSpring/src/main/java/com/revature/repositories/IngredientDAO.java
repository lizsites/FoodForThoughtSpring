package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Ingredient;

public interface IngredientDAO extends JpaRepository<Ingredient, Integer> {

}
