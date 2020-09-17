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

@Entity 
@Table (name ="steps")
public class Steps {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="steps_id")
		private int id;
		
		@Column(name = "body")
		private String body;
		
		@Column(name ="order")
		private int order;
		
		@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
		@JoinColumn(name="recipe_id")
		private Recipe recipe;

		public Steps() {
			super();
		}

		public Steps(int id, String body, int order, Recipe recipe) {
			super();
			this.id = id;
			this.body = body;
			this.order = order;
			this.recipe = recipe;
		}

		public Steps(String body, int order, Recipe recipe) {
			super();
			this.body = body;
			this.order = order;
			this.recipe = recipe;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((body == null) ? 0 : body.hashCode());
			result = prime * result + id;
			result = prime * result + order;
			result = prime * result + ((recipe == null) ? 0 : recipe.hashCode());
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
			if (order != other.order)
				return false;
			if (recipe == null) {
				if (other.recipe != null)
					return false;
			} else if (!recipe.equals(other.recipe))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Steps [id=" + id + ", body=" + body + ", order=" + order + ", recipe=" + recipe + "]";
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

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public Recipe getRecipe() {
			return recipe;
		}

		public void setRecipe(Recipe recipe) {
			this.recipe = recipe;
		}

}
