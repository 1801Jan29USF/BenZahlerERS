package com.revature.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseUtil {
	private Logger log = Logger.getRootLogger();
	public void writeObjectToResponse(Object obj, HttpServletResponse response) throws IOException, ServletException {
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(obj);
		
		log.trace("Writing response.");
		
//		// write the json to the body of the request
		response.setContentType("application/json");
		response.getWriter().println(json);
	}
}
