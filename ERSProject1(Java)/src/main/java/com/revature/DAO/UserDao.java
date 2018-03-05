package com.revature.DAO;

import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;

public interface UserDao {
	int register(User u);
	boolean exists(String username);
	boolean exists(int id);
	User getUser(String username);
	List<Reimbursement> getUserHistory(int id);
	boolean newReimbursement(Reimbursement reimb);
	List<Reimbursement> viewAllHistory();
	boolean updateReimbStatus(Reimbursement reimb);
	List<Reimbursement> getRequestsByStatus(int status);
	List<Reimbursement> getUserPending(int id);
}
