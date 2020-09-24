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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Column(name = "user_name", unique = true)
	private String username;

	@Column(name = "food_password")
	private String password;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade = CascadeType.ALL)
	
	private List<Recipe> recipes;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {/*CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH*/ CascadeType.ALL})
	@JsonManagedReference(value="pictures")
	//@JoinColumn(name="picture_id", referencedColumnName="id")
	private List<Picture> pictures;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "preference_id")
	
	private Preferences preference;

	public User() {
		super();
	}

	public User(int id, String username, String password, List<Recipe> recipes, List<Picture> pictures,
			Preferences preference) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.recipes = recipes;
		this.pictures = pictures;
		this.preference = preference;
	}

	public User(String username, String password, List<Recipe> recipes, List<Picture> pictures,
			Preferences preference) {
		super();
		this.username = username;
		this.password = password;
		this.recipes = recipes;
		this.pictures = pictures;
		this.preference = preference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((pictures == null) ? 0 : pictures.hashCode());
		result = prime * result + ((preference == null) ? 0 : preference.hashCode());
		result = prime * result + ((recipes == null) ? 0 : recipes.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (pictures == null) {
			if (other.pictures != null)
				return false;
		} else if (!pictures.equals(other.pictures))
			return false;
		if (preference == null) {
			if (other.preference != null)
				return false;
		} else if (!preference.equals(other.preference))
			return false;
		if (recipes == null) {
			if (other.recipes != null)
				return false;
		} else if (!recipes.equals(other.recipes))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", recipes=" + recipes
				+ ", pictures=" + pictures + ", preference=" + preference + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}
	
	public void addPicture(Picture picture, boolean set) {
		if(picture != null) {
			getPictures().add(picture);
			if (set) {
				picture.setUser(this, false);
			}
		}
	}

	public Preferences getPreference() {
		return preference;
	}

	public void setPreference(Preferences preference) {
		this.preference = preference;
	}
	

	}