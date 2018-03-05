package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.services.ManagerService;
import com.revature.util.ResponseUtil;

public class ManagerController implements HttpController {
	private Logger log = Logger.getRootLogger();
	private ManagerService manService = new ManagerService();
	private ResponseUtil responseUtil = new ResponseUtil();
	
	/**
	 *	ViewAllHistory
	 *	GetUserHistory
	 *	getRequestsByStatus
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String url = request.getPathInfo();
		
		// View all history
		if(url.startsWith("/manager/history/all")) {
			log.trace("Request made to view all reimbursement history.");
			List<Reimbursement> reimbList = manService.getAllHistory();
			responseUtil.writeObjectToResponse(reimbList, response);
			
		// View user history
		} else if(url.startsWith("/manager/history/")) {
			int id = Integer.parseInt(url.substring("/manager/history/".length()));
			log.trace("Request made to view history of user: " + id);
			
			List<Reimbursement> reimbList = manService.getUserHistory(id);
			responseUtil.writeObjectToResponse(reimbList, response);
			
		// View by status
		} else if(url.startsWith("/manager/status/")) {
			log.trace("Request made to view requests by status.");
			int status = Integer.parseInt(url.substring("/manager/status/".length()));
			
			List<Reimbursement> reimbList = manService.getByStatus(status);
			responseUtil.writeObjectToResponse(reimbList, response);
		}
	}
	
	/**
	 *	Register
	 *	UpdateReimbStatus
	 *	newReimbursement
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String url = request.getPathInfo();
		ObjectMapper objectMap = new ObjectMapper();
		
		// Register new user
		if(url.startsWith("/manager/register/")) {
			String json = request.getReader().lines().reduce( (acc, cur) -> acc + cur).get();
			User u = (User) objectMap.readValue(json, User.class);
			int id = manService.newUser(u);
			if(id < 0) {
				log.trace("There was a problem inserting a new user.");
			}
			String respJson = objectMap.writeValueAsString(u);
			response.getWriter().write(respJson);
			
			
		// Update a reimbursement's status
		} else if (url.startsWith("/manager/update/")) {
			String json = request.getReader().lines().reduce( (acc, cur) -> acc + cur).get();
			Reimbursement reimb = (Reimbursement) objectMap.readValue(json, Reimbursement.class);
			if(manService.updateReimb(reimb)) {
				String respJson = objectMap.writeValueAsString(reimb);
				response.getWriter().write(respJson);
			} else {
				// Error 400 - Bad Request
				response.setStatus(400);
			}
			
		// Create a new reimbursement
		} else if (url.startsWith("/manager/newReimbursement/")) {
			String json = request.getReader().lines().reduce( (acc, cur) -> acc + cur).get();
			Reimbursement reimb = (Reimbursement) objectMap.readValue(json, Reimbursement.class);
			if(manService.newReimb(reimb)) {
				String respJson = objectMap.writeValueAsString(reimb);
				response.getWriter().write(respJson);
			} else {
				// Error 400 - Bad Request (presumably not unique)
				response.setStatus(400);
			}
		}
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
