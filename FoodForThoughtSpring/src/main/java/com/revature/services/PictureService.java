package com.revature.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Picture;
import com.revature.models.User;
import com.revature.repositories.IPicturesDAO;

@Service
public class PictureService {
	//service class for saving/retrieving pictures
	private IPicturesDAO picDao;
	private LoginService ls;
	
	@Autowired
	public PictureService(IPicturesDAO picDao, LoginService ls) {
		this.picDao = picDao;
		this.ls = ls;
	}
	
	public Optional<Picture> findById(int id) {
		return picDao.findById(id);
	}

	public void savePicture(User u, Picture pic) {
		u.addPicture(pic, true);
		System.out.println("picture being added :" + pic);
		System.out.println("user after adding pic" + u);
		picDao.save(pic);
	}

	public Optional<Picture> findByUser(String username) {
		User u = ls.findUser(username);
		return picDao.findByUser(u);
	}
}
