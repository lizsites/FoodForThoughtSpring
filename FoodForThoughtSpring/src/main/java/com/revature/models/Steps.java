package com.revature.models;

import javax.persistence.CascadeType;
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

@Component
@Entity 
@Table (name ="steps")
public class Steps {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="steps_id")
		private int id;
		
		@Column(name = "body")
		private String body;
		
		@Column(name ="step_num")
		private int stepNum;
		
		@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
		@JoinColumn(name="recipe_id")
		@JsonBackReference
		private Recipe recipe;

		public Steps() {
			super();
		}

		public Steps(int id, String body, int stepNum, Recipe recipe) {
			super();
			this.id = id;
			this.body = body;
			this.stepNum = stepNum;
			this.recipe = recipe;
		}

		public Steps(String body, int stepNum, Recipe recipe) {
			super();
			this.body = body;
			this.stepNum = stepNum;
			this.recipe = recipe;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((body == null) ? 0 : body.hashCode());
			result = prime * result + id;
			result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
			result = prime * result + stepNum;
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
			Steps other = (Steps) obj;
			if (body == null) {
				if (other.body != null)
					return false;
			} else if (!body.equals(other.body))
				return false;
			if (id != other.id)
				return false;
			if (recipe == null) {
				if (other.recipe != null)
					return false;
			} else if (!recipe.equals(other.recipe))
				return false;
			if (stepNum != other.stepNum)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Steps [id=" + id + ", body=" + body + ", stepNum=" + stepNum + ", recipe=" + recipe + "]";
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

		public int getStepNum() {
			return stepNum;
		}

		public void setStepNum(int stepNum) {
			this.stepNum = stepNum;
		}

		public Recipe getRecipe() {
			return recipe;
		}

		public void setRecipe(Recipe recipe) {
			this.recipe = recipe;
		}
		
}
