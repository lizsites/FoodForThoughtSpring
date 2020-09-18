package com.revature.models;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name = "preferences")
public class Preferences {
	
	//what other fields do we want in our preferences???

	public enum DietType {
		GLUTEN_FREE, VEGAN, VEGETARIAN, LACTO_VEGETARIAN, KETOGENIC, OVO_VEGETARIAN, PESCETARIAN, PALEO, PRIMAL, WHOLE30
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="preference_id")
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_diet")
	private DietType dietType;

	@Column(name = "min_calories", nullable=true)
	private int minCalories;

	@Column(name = "max_calories", nullable=true)
	private int maxCalories;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "preference", cascade = CascadeType.ALL)
	@JsonBackReference
	private User users;

	public Preferences() {
		super();
	}

	public Preferences(int id, DietType dietType, int minCalories, int maxCalories) {
		super();
		this.id = id;
		this.dietType = dietType;
		this.minCalories = minCalories;
		this.maxCalories = maxCalories;
	}

	public Preferences(DietType dietType, int minCalories, int maxCalories) {
		super();
		this.dietType = dietType;
		this.minCalories = minCalories;
		this.maxCalories = maxCalories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dietType == null) ? 0 : dietType.hashCode());
		result = prime * result + id;
		result = prime * result + maxCalories;
		result = prime * result + minCalories;
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
		Preferences other = (Preferences) obj;
		if (dietType != other.dietType)
			return false;
		if (id != other.id)
			return false;
		if (maxCalories != other.maxCalories)
			return false;
		if (minCalories != other.minCalories)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Preferences [id=" + id + ", dietType=" + dietType + ", minCalories=" + minCalories + ", maxCalories="
				+ maxCalories + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DietType getDietType() {
		return dietType;
	}

	public void setDietType(DietType dietType) {
		this.dietType = dietType;
	}

	public int getMinCalories() {
		return minCalories;
	}

	public void setMinCalories(int minCalories) {
		this.minCalories = minCalories;
	}

	public int getMaxCalories() {
		return maxCalories;
	}

	public void setMaxCalories(int maxCalories) {
		this.maxCalories = maxCalories;
	}


}
