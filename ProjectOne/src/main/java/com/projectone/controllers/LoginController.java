package com.projectone.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projectone.dao.UserDao;
import com.projectone.services.AccountService;

public class LoginController {
	
	private static UserDao uDao= new UserDao();
	private static AccountService aServ = new AccountService(uDao);
	
	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		//To read in stringified Json data from a post request is more complicated that reading form data
		StringBuilder builder = new StringBuilder();
		
	}

}
