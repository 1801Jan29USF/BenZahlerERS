package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.DAO.UserDAOjdbc;
import com.revature.beans.Reimbursement;

public class EmployeeService {
	private Logger log = Logger.getRootLogger();
	UserDAOjdbc userDao = new UserDAOjdbc();
	
	public List<Reimbursement> getUserHistory(int id) {
		log.trace("Employee Service calling Dao to get user history.");
		return userDao.getUserHistory(id);
	}

	public List<Reimbursement> getUserPending(int id) {
		log.trace("Employee Service calling dao to get user pending.");
		return userDao.getUserPending(id);
	}

	/**
	 * Adds the current time and sets status to 0 of newly submitted reimbursements
	 */
	public boolean newReimb(Reimbursement reimb) {
		log.trace("Employee service calling dao to create new reimbursement.");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		reimb.setSubmitted(timestamp);
		reimb.setStatusId(0);
		
		return userDao.newReimbursement(reimb);
	}
}
