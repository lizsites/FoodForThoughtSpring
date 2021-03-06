package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.revature.models.Picture;

import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.services.PictureService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders="*")
@ResponseBody
public class PictureController {
	private Logger log  = LogManager.getLogger(PictureController.class);
	private PictureService ps;
	private HttpSession sess;
	private LoginService login;
	@Autowired
	public PictureController(PictureService ps, HttpSession sess, LoginService login) {
		this.ps = ps;
		this.sess = sess;
		this.login = login;
	}

	@PostMapping(path="/upload")
	public ResponseEntity<User> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("username") String username) {
		if (file != null && username != null) {
			Picture pic = new Picture();
			User u = login.findUser(username);
			
			System.out.println("session being passed:" + sess);
			System.out.println("Sessioned user right before uploading pic: " + u);

			try {
				//file.getBytes throws IOException
				pic.setPicture(file.getBytes());
				//new implementation for service method savePicture combines setting up and saving the picture
				ps.savePicture(u, pic);
				log.info("New picture uploaded by User " + username);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return ResponseEntity.status(HttpStatus.CREATED).body(u);
		} else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping(path = "/download")
	public ResponseEntity<byte[]> getById(@RequestBody String username) {

		List<Picture> pics = ps.findAllByUser(username);
		List<byte[]> bodies = new ArrayList<byte[]>();
	
			bodies.add(pics.get(0).getPicture());
			System.out.println(bodies.get(0));
		if (!bodies.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(bodies.get(0));
			//will this just return a byte stream? how to turn into pic?
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

}