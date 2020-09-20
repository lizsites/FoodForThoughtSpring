package com.revature.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name="ingredients")
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ingredient_id")
	private int id;
	@Column(name="ingredient_name")
	private String name;
	
	@Column(name="ingredient_cals")
	private int cals;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="recipe_id")
//	@JsonBackReference(value="recipeIngred")
	@JsonIgnore
	private Recipe recipe;
	
	public Ingredient(int id, String name, int cals, Recipe recipe) {
		super();
		this.id = id;
		this.name = name;
		this.cals = cals;
		this.recipe = recipe;
	}

	public Ingredient(String name, int cals, Recipe recipe) {
		super();
		this.name = name;
		this.cals = cals;
		this.recipe = recipe;
	}

	
	public Ingredient() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCals() {
		return cals;
	}

	public void setCals(int cals) {
		this.cals = cals;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", cals=" + cals
				+ "]";
	}
	
	

}
