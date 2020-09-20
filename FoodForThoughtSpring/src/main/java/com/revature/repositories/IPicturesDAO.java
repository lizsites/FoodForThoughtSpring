package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.revature.models.Picture;

public interface IPicturesDAO extends JpaRepository<Picture, Integer> {
	
}
