package com.revature.models;

public class recipeDTO {
	public String username;
	public Recipe recipe;
	
	public recipeDTO(String username, Recipe recipe) {
		super();
		this.username = username;
		this.recipe = recipe;
	}
	public recipeDTO() {
		super();
	}
	@Override
	public String toString() {
		return "recipeDTO [username=" + username + ", recipe=" + recipe + "]";
	}
	
	
}
