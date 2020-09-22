package com.revature.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Picture;
import com.revature.repositories.IPicturesDAO;

@Service
public class PictureService {
	//service class for saving/retrieving pictures
	private IPicturesDAO picDao;
	
	@Autowired
	public PictureService(IPicturesDAO picDao) {
		this.picDao = picDao;
	}
	
	public void savePicture(Picture p) {
		picDao.save(p);
	}
	
	public Optional<Picture> findById(int id) {
		return picDao.findById(id);
	}
}
