package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.User;
import com.revature.services.LoginService;

public class LoginController implements HttpController {
	private Logger log = Logger.getRootLogger();
	private LoginService loginService = new LoginService();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Calls login service to try to log in
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String json = request.getReader().lines().reduce( (acc, cur) -> acc + cur).get();
		ObjectMapper objectMap = new ObjectMapper();
		User credentials = (User) objectMap.readValue(json, User.class);
		
		User u = loginService.login(credentials.getUsername(), credentials.getPassword());
		if(u == null) {
			response.setStatus(401);
		}
		String respJson = objectMap.writeValueAsString(u);
		response.getWriter().write(respJson);		
	}

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

}
