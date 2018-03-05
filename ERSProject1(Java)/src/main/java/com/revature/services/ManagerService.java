package com.revature.services;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.DAO.UserDAOjdbc;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;

public class ManagerService {
	private Logger log = Logger.getRootLogger();
	UserDAOjdbc userDao = new UserDAOjdbc();

	public List<Reimbursement> getAllHistory() {
		log.trace("Manager Service calling Dao to get all history.");
		return userDao.viewAllHistory();
	}

	public List<Reimbursement> getUserHistory(int id) {
		log.trace("Manager Service calling Dao to get history of user: " + id);
		return userDao.getUserHistory(id);
	}

	public boolean newReimb(Reimbursement reimb) {
		log.trace("Manager Service calling Dao to create new reimbursement.");
		return userDao.newReimbursement(reimb);
	}

	/**
	 * Adds current timestamp as the resolved time
	 */
	public boolean updateReimb(Reimbursement reimb) {
		log.trace("Manager Service calling Dao to update a reimbursement.");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		reimb.setResolved(timestamp);
		return userDao.updateReimbStatus(reimb);
	}

	/**
	 * New users are all created as employees (role_id 0) 
	 */
	public int newUser(User u) {
		log.trace("Manager Service calling Dao to create a new Employee.");
		u.setRole_id(0);
		return userDao.register(u);
	}

	public List<Reimbursement> getByStatus(int status) {
		log.trace("Manager Service calling Dao to view requests by status: " + status);
		return userDao.getRequestsByStatus(status);
	}

}
