package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImp;

import com.revature.models.User;

import com.revature.services.LoginService;

@Controller
@RequestMapping(path="/login")
@ResponseBody
public class LoginController {
	
	@PostMapping
	public void login() {
		
		
		if (ls.login(u)) {
			
			UserDAO userDAO = new UserDAOImp();
			HttpSession sesh = req.getSession();
			User f = userDAO.getUserByUsername(u.getUsername());
			u = f;
			sesh.setAttribute("user", u);
			sesh.setAttribute("loggedin" , true);
			
			
	}
}
	

	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession sess = req.getSession(false);
		if (sess != null && (boolean)sess.getAttribute("loggedin")) {
			sess.invalidate();
			res.setStatus(201);
		} else {
			res.setStatus(403);
			res.getWriter().println("You must be logged in to log out");
		}
	}
	
	public void updateUser(HttpServletRequest req, HttpServletResponse res) throws IOException{
		HttpSession sess = req.getSession(false);
		if (sess != null && (boolean)sess.getAttribute("loggedin")) {
			ObjectMapper om = new ObjectMapper();
			LoginService ls = new LoginService();
			BufferedReader reader = req.getReader();

			StringBuilder sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb);
			User newU = om.readValue(body, User.class);
			System.out.println("user info to be updated : " +newU);
			User u = (User)sess.getAttribute("user");
			System.out.println("user info from session : " + u);
			u.setPassword(newU.getPassword());
			if (ls.updateUser(u)) {
				res.setStatus(200);
				res.getWriter().println(u);
			} else {
				res.setStatus(403);
				res.getWriter().println("user could not be updated");
			}
		}
	}
	public void addUser(HttpServletRequest req, HttpServletResponse res) throws IOException{
		ObjectMapper om = new ObjectMapper();
		LoginService ls = new LoginService();
		BufferedReader reader = req.getReader();

		StringBuilder sb = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String body = new String(sb);
		System.out.println(body);
		User u = om.readValue(body, User.class);
		if (ls.register(u)) {
			
			HttpSession sesh = req.getSession();
			sesh.setAttribute("user", u);
			sesh.setAttribute("loggedin" , true);
			String jsonU = om.writeValueAsString(u);

			res.getWriter().println(jsonU);

			res.setStatus(200);

		}
	}


}
