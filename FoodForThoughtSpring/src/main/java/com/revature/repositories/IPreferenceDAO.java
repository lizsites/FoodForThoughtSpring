package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Preferences;

public interface IPreferenceDAO extends JpaRepository<Preferences, Integer>{

}
