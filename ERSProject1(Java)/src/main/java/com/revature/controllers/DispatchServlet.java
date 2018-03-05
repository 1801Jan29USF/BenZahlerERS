package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlets.DefaultServlet;
import org.apache.log4j.Logger;

/**
 * Noting here that we are only catching GET and POST requests
 * 
 * @author Maara
 *
 */
public class DispatchServlet extends DefaultServlet {
	private Logger log = Logger.getRootLogger();
	private LoginController loginControl = new LoginController();
	private EmployeeController empControl = new EmployeeController();
	private ManagerController manControl = new ManagerController();
	
	/**
	 * To be honest...
	 * I'm not really 100% sure what we did here. But apparently it allows us to communicate with our angular server
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers","Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
	    
	    resp.setContentType("application/json");
	}
	
	/**
	 * Catches all get requests. If its static, send it back so that it can grab the static page it needs.
	 * Otherwise, parse the URL and forward it to the appropriate controller
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = request.getPathInfo();
		log.trace("Get request made with path " + url);
		if(url.startsWith("/static/")) {
			super.doGet(request, response);
			return;
		} else {
			if(url.startsWith("/login")) {
//				loginControl.doGet(request, response);
			} else if(url.startsWith("/employee")) {
				empControl.doGet(request, response);
			} else if (url.startsWith("/manager")) {
				manControl.doGet(request, response);
			}
		}
	}
	
	/**
	 * Catches all http post requests.
	 * Forwards them to the appropriate controller
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = request.getPathInfo();
		log.trace("Post request made with path " + url);
			if(url.startsWith("/login")) {
				loginControl.doPost(request, response);
			} else if(url.startsWith("/employee")) {
				empControl.doPost(request, response);
			} else if (url.startsWith("/manager")) {
				manControl.doPost(request, response);
			}
	}
}
