package com.revature.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.multipart.MultipartFile;

import com.revature.models.Picture;
import com.revature.models.User;

public interface IPicturesDAO extends JpaRepository<Picture, Integer> {

	List<Picture> findByUser(User u);
	
}
