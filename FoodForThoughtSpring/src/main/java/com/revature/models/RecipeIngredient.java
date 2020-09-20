package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

//@Component
//@Entity
//@Table(name ="recipe_ingredient")
public class RecipeIngredient {
//
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="recipe_ingredient_id")
//	private int id;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "recipe_id")
//	@JsonBackReference
//	private Recipe recipe;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ingredient_id")
//	private Ingredient ingredient;
//
//	@Column(name = "ingredient_amount")
//	private String amount;
//
//
//	public RecipeIngredient(Recipe recipe, Ingredient ingredient, String amount) {
//		super();
//		this.recipe = recipe;
//		this.ingredient = ingredient;
//		this.amount = amount;
//	}
//
//	
//	
//	public RecipeIngredient(int id, Recipe recipe, Ingredient ingredient, String amount) {
//		super();
//		this.id = id;
//		this.recipe = recipe;
//		this.ingredient = ingredient;
//		this.amount = amount;
//	}
//
//
//
//	public RecipeIngredient() {
//		super();
//	}
//
//
//	public RecipeIngredient(String amount) {
//		super();
//		this.amount = amount;
//	}
//
//	public Recipe getRecipe() {
//		return recipe;
//	}
//
//	public void setRecipe(Recipe recipe) {
//		this.recipe = recipe;
//	}
//
//	public Ingredient getIngredient() {
//		return ingredient;
//	}
//
//	public void setIngredient(Ingredient ingredient) {
//		this.ingredient = ingredient;
//	}
//
//	public String getAmount() {
//		return amount;
//	}
//
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}
//
//
//
//	@Override
//	public String toString() {
//		return "RecipeIngredient [id=" + id + ", ingredient=" + ingredient + ", amount=" + amount
//				+ "]";
//	}
	
	
}
