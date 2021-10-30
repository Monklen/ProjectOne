package com.projectone.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.projectone.controllers.LoginController;

public class Dispatcher {
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("In the servlet dispatcher with URI: " + req.getRequestURI());
		
		switch(req.getRequestURI()) {
			case "/ProjectOne/api/login":
				LoginController.login(req, res);
				break;
		}
	}

}
