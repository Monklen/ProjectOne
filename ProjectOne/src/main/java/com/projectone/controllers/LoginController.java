package com.projectone.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectone.dao.UserDao;
import com.projectone.models.User;
import com.projectone.services.AccountService;

public class LoginController {
	
	private static UserDao uDao= new UserDao();
	private static AccountService aServ = new AccountService(uDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		//To read in stringified Json data from a post request is more complicated that reading form data
		StringBuilder builder = new StringBuilder();
		
		//this buffered reader will read json data line by line
		BufferedReader reader = req.getReader();
		
		String line;
		while((line = reader.readLine()) != null) {
			builder.append(line);
			builder.append(System.lineSeparator());
		}
		
		String data = builder.toString();
		System.out.println(data);
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode parsedObj = mapper.readTree(data);
		
		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();
		
		try {
			System.out.println("in the post handler");
			User u = aServ.login(username, password);
			System.out.println(u);
			//keep track of if a user is signed in by storing their id in the session
			req.getSession().setAttribute("id", u.getId());
			res.setStatus(200);
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
		}
		catch(Exception e) {
			
			res.setStatus(403);
			res.getWriter().println("username or password is incorrect");
			
		}
	}

}
