package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.revature.models.User;
import com.revature.utilities.HibernateUtil;

import java.util.Optional;

import org.hibernate.Session; 
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public interface IUserDAO extends JpaRepository<User, Integer>{
// why integer?
	
	User findByUsername(String username);
	
	
		
	

}
