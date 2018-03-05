package com.revature.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpController {
	void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
	void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
