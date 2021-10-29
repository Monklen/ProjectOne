package com.projectone.services;

import java.sql.SQLException;

import com.projectone.exceptions.AccountDoesNotExistException;
import com.projectone.exceptions.InvalidCredentialsException;
import com.projectone.exceptions.UsernameAlreadyExistsException;
import com.projectone.logging.Logging;
import com.projectone.dao.UserDao;
import com.projectone.models.User;

public class AccountService {
	
	private UserDao uDao;
	
	public AccountService(UserDao u) {
		this.uDao = u;
	}
	
	public User signup(String first, String last, String email, String username, String password, String usertype) throws UsernameAlreadyExistsException {
		
		User u = new User();
		
		try {
			uDao.createUser(u);
			Logging.logger.info("New account has been created");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			Logging.logger.warn("Account already exists");
			throw new UsernameAlreadyExistsException();
		}
		
		return u;
	}
	
	public User login(String username, String password) throws AccountDoesNotExistException, InvalidCredentialsException{
		
		User u = uDao.getUserByUsername(username);
		
		if(u.getId() == 0) {
			Logging.logger.warn("Account tried loggin in does not exist");
			throw new AccountDoesNotExistException();
		}
		else if(!u.getPassword().equals(password)) {
			Logging.logger.warn("Invalid credentials");
			throw new InvalidCredentialsException();
		}
		else {
			Logging.logger.info("Account logged in");
			return u;
		}
	}
	
	

}
