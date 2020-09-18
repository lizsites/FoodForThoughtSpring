package com.revature;



import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication; 
import org.springframework.boot.autoconfigure.SpringBootApplication;



import com.revature.models.Picture;
import com.revature.models.Preferences;
import com.revature.models.Recipe;
import com.revature.models.Steps;
import com.revature.models.User;
import com.revature.models.Preferences.DietType;


@SpringBootApplication
public class FoodForThoughtSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodForThoughtSpringApplication.class, args);
		
	}

}
