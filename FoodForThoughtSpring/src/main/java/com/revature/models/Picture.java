package com.revature.models;

//import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
//import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
@Table(name = "pictures")
public class Picture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "picture_id")
	private int id;

//	@Column(name = "picture_name")
//	private String name;

//	@Lob
	
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(name = "picture")
	private byte imageAsBytea[];

	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "user_id")
	@JsonBackReference(value="pictures")
	private User user;

	public Picture(int id, byte[] image, User user) {
		super();
		this.id = id;
		this.imageAsBytea = image;
		this.user = user;
	}

	public Picture(byte[] image, User user) {
		super();
		this.imageAsBytea = image;
		this.user = user;
	}

	public Picture() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getPicture() {
		return imageAsBytea;
	}

	public void setPicture(byte[] image) {
		this.imageAsBytea = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user, boolean add) {
		this.user = user;
		if(this.user != null && add) {
			user.addPicture(this, false);
		}
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", picture=" + imageAsBytea + ", user=" + user.getId() + "]";
	}

}
