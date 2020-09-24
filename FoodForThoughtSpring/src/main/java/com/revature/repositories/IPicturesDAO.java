package com.revature.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.revature.models.Picture;

public interface IPicturesDAO extends JpaRepository<Picture, Integer> {

	Optional<Picture> findByUserName(String username);
	
}
