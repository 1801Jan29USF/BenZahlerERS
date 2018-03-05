package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.util.ResponseUtil;

/**
 * Can be directed here by the dispatch servlet
 * 
 * @author Maara
 *
 */
public class EmployeeController implements HttpController {
	private Logger log = Logger.getRootLogger();
	private EmployeeService empService = new EmployeeService();
	private ResponseUtil responseUtil = new ResponseUtil();
	
	
	/**
	 * GET REQUESTS
	 * Parses the URL to further determine what the request is trying to do.
	 * -Get a specific user's history
	 * -Get user's pending reimbursements
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String url = request.getPathInfo();
	
		if(url.startsWith("/employee/history/")) {
			int id = Integer.parseInt(url.substring("/employee/history/".length()));
			log.trace("Request made to get user history with id: " + id);
			
			List<Reimbursement> reimbList = empService.getUserHistory(id);
			responseUtil.writeObjectToResponse(reimbList, response);	
			
		} else if (url.startsWith("/employee/status/")) {
			int id = Integer.parseInt(url.substring("/employee/status/".length()));
			log.trace("Request made to get reimbursements by status for user with id: " + id);
			
			List<Reimbursement> reimbList = empService.getUserPending(id);
			responseUtil.writeObjectToResponse(reimbList, response);
		}
		
	}

	/**
	 * POST REQUESTS
	 * Further parses the URL to figure out what the request is trying to do
	 * - Create a new reimbursement
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String json = request.getReader().lines().reduce( (acc, cur) -> acc + cur).get();
		ObjectMapper objectMap = new ObjectMapper();
		Reimbursement reimb = (Reimbursement) objectMap.readValue(json, Reimbursement.class);
		log.trace(reimb);
		
		if(empService.newReimb(reimb)) {
			String respJson = objectMap.writeValueAsString(reimb);
			response.getWriter().write(respJson);
			
		} else {
			// Error 400 - Bad Request (presumably not unique)
			response.setStatus(400);
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
