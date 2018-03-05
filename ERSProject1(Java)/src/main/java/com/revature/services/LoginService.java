package com.revature.services;

import org.apache.log4j.Logger;

import com.revature.DAO.UserDAOjdbc;
import com.revature.beans.User;

public class LoginService {
	private Logger log = Logger.getRootLogger();
	UserDAOjdbc userDao = new UserDAOjdbc();
	
	/**
	 * Calls dao to get user information
	 * Checks if credentials match
	 */
	public User login (String username, String password) {
		User u = userDao.getUser(username);
		if(u == null) {
			log.trace("Username does not exist.");
			return null;
		}
		
		log.trace("User exists. Checking credentials.");
		if(password.equals(u.getPassword())) {
			log.trace("Credentials are a match.");
			return u;
		}
		log.trace("Credentials did not match.");
		return null;
	}
	
	
}
