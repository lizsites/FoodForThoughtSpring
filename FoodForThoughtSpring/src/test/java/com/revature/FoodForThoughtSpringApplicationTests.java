package com.revature;

import org.junit.jupiter.api.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FoodForThoughtSpringApplicationTests extends Runner {

	public static void main(String[] args) {
		SpringApplication.run(FoodForThoughtSpringApplication.class, args);
		
	}

	@Override
	public Description getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void run(RunNotifier notifier) {
		// TODO Auto-generated method stub
		
	
}
}
