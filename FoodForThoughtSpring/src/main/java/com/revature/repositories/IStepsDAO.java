package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Steps;

public interface IStepsDAO extends JpaRepository<Steps, Integer> {

}
