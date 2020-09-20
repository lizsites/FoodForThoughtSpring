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

@Component
@Entity
@Table(name="recipes")
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recipe_id")
	private int id;

	@Column(name="recipe_body")
	private String body;
	
	@Column(name="recipe_cals")
	private double cals;
	
	@Column(name="recipe_title", nullable=false)
	String title;
	
	@OneToMany(mappedBy="recipe", fetch = FetchType.LAZY)
	
	private List<Ingredient> ingredients;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="user_id")
	@JsonBackReference
	private User owner;
	
	@OneToMany(mappedBy="recipe", fetch = FetchType.LAZY)
	
	private List<Steps> recipeSteps;

	public Recipe() {
		super();
	}

	public Recipe(int id, String body, double cals, String title, List<Ingredient> ingredients, User owner,
			List<Steps> step) {
		super();
		this.id = id;
		this.body = body;
		this.cals = cals;
		this.title = title;
		this.ingredients= ingredients;
		this.owner = owner;
		this.recipeSteps = step;
	}

	public Recipe(String body, double cals, String title, List<Ingredient> ingredients, User owner,
			List<Steps> step) {
		super();
		this.body = body;
		this.cals = cals;
		this.title = title;
		this.ingredients= ingredients;
		this.owner = owner;
		this.recipeSteps = step;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		//result = prime * result + cals;
		result = prime * result + id;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
		result = prime * result + ((recipeSteps == null) ? 0 : recipeSteps.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (cals != other.cals)
			return false;
		if (id != other.id)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		if (recipeSteps == null) {
			if (other.recipeSteps != null)
				return false;
		} else if (!recipeSteps.equals(other.recipeSteps))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", body=" + body + ", cals=" + cals + ", title=" + title + ", ingredients="
				+ ingredients + ", owner=" + owner + ", steps=" + recipeSteps.size() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getCals() {
		return cals;
	}

	public void setCals(double cals) {
		this.cals = cals;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setRecipeIngredient(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Steps> getRecipeStep() {
		return recipeSteps;
	}

	public void setRecipeStep(List<Steps> step) {
		this.recipeSteps = step;
	}
	
}
