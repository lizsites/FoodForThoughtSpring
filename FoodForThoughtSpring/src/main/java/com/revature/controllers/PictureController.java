package com.revature.controllers;

import java.io.IOException;
import java.util.Optional;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.revature.models.Picture;

import com.revature.models.User;
import com.revature.repositories.IPicturesDAO;

@Controller
public class PictureController {

	private IPicturesDAO picturesDAO;
	private HttpSession sess;

	@Autowired
	public PictureController(IPicturesDAO picturesDAO, HttpSession sess) {
		this.picturesDAO = picturesDAO;
		this.sess = sess;
	}

//	@PostMapping("/")
	@PostMapping
	public ResponseEntity<Picture> handleFileUpload(@RequestParam("file") MultipartFile file) {
		if (file != null) {
			Picture pic = new Picture();
			User u = (User) sess.getAttribute("user");
			System.out.println("Sessioned user right before uploading pic: " + u);
			pic.setUser(u);
			try {
				pic.setPicture(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			picturesDAO.save(pic);
			return ResponseEntity.status(HttpStatus.CREATED).body(pic);
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Picture> getById(@PathVariable("id") int id) {
		Optional<Picture> pic = picturesDAO.findById(id);

		if (pic.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(pic.get());
			//will this just return a byte stream? how to turn into pic?
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}