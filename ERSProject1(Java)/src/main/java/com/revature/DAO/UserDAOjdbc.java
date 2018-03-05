package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.util.ConnectionUtility;

public class UserDAOjdbc implements UserDao {
	private Logger log = Logger.getRootLogger();
	private ConnectionUtility connUtil = ConnectionUtility.getConnectionUtil();

	@Override
	public int register(User u) {
		log.trace("Method called to register new employee.");
		log.trace("Attempting to connect to the database.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection successful.");
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO ers_users(username, password, user_first_name, user_last_name, user_email, user_role_id) VALUES(?, ?, ?, ?, ?, ?)",
					new String[] { "user_id" });
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getUser_first_name());
			ps.setString(4, u.getUser_last_name());
			ps.setString(5, u.getUser_email());
			ps.setInt(6, u.getRole_id());
			log.trace("Statement Prepared");

			int rowsInserted = ps.executeUpdate();
			log.debug("Query inserted " + rowsInserted + " row(s) into the db.");
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				u.setUser_id(rs.getInt(1));
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			log.warn("Failed to insert new user.");
		}
		return -1;
	}

	@Override
	public boolean exists(String username) {
		log.trace("Method called to check if user exists.");
		log.trace("Attempting to connect to database.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection successful.");
			PreparedStatement ps = conn.prepareStatement("SELECT username FROM ers_users WHERE username = ?");
			ps.setString(1, username);
			log.trace("Prepared statement worked");
			ResultSet rs = ps.executeQuery();
			log.trace("After execution.");
			if (rs.next()) {
				// If there's anything in the result set it will be the username
				return true;
			} else {
				// Select still returns a result set if it doesn't find anything, its just empty
				return false;
			}
		} catch (SQLException e) {
			log.warn("SQL error. Returning false");
		}
		return false;
	}

	@Override
	public boolean exists(int id) {
		log.trace("Method called to check if user exists.");
		log.trace("Attempting to connect to database.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection successful.");
			PreparedStatement ps = conn.prepareStatement("SELECT user_id FROM ers_users WHERE user_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// If there's anything in the result set it will be the username
				return true;
			} else {
				// Select still returns a result set if it doesn't find anything, its just empty
				return false;
			}
		} catch (SQLException e) {
			log.warn("SQL error. Method 'exists' returns false by default.");
		}
		return false;
	}

	@Override
	public User getUser(String username) {
		log.trace("Method called to get User with username " + username);
		log.trace("Attempting to connect to database.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection successful.");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM ers_users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User newUser = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"),
						rs.getString("user_first_name"), rs.getString("user_last_name"), rs.getString("user_email"),
						rs.getInt("user_role_id"));
				return newUser;
			} else {
				log.warn("No user found with name " + username);
			}
		} catch (SQLException e) {
			log.warn("Error. Failed to retrieve user.");
		}
		return null;
	}

	@Override
	public List<Reimbursement> getUserHistory(int id) {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		log.trace("Method called to get history of user " + id);
		log.trace("Attempting to connect to the database.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection Successful.");
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reimbursement WHERE reimb_author = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Reimbursement newReimb = new Reimbursement(rs.getInt("reimb_id"), rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
				reimbList.add(newReimb);
			}

			return reimbList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.warn("Failed to retrieve user history.");
		}

		return null;
	}

	@Override
	public boolean newReimbursement(Reimbursement reimb) {
		log.trace("Method called to create new reimbursement.");
		log.trace("Attempting to connect to the database.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection successful.");
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver, reimb_status_id, reimb_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
					new String[] { "reimb_id" });
			ps.setFloat(1, reimb.getAmount());
			ps.setTimestamp(2, reimb.getSubmitted());
			ps.setTimestamp(3, reimb.getResolved());
			ps.setString(4, reimb.getDescription());
			ps.setInt(5, reimb.getAuthorId());
			ps.setInt(6, reimb.getResolverId());
			ps.setInt(7, reimb.getStatusId());
			ps.setInt(8, reimb.getTypeId());

			int rowsInserted = ps.executeUpdate();
			log.debug("Query inserted " + rowsInserted + " row(s) into the db.");
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				// reimb.set (rs.getInt(1));
				return true;
			}
		} catch (SQLException e) {
			log.warn("Failed to insert new reimbursement.");
		}
		return false;
	}

	@Override
	public List<Reimbursement> viewAllHistory() {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		log.trace("Method called to view All History.");
		log.trace("Attempting to connect to database.");
		try(Connection conn = connUtil.getConnection()) {
			log.trace("Connection Successful.");
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reimbursement");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement newReimb = new Reimbursement(rs.getInt("reimb_id"), rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
				reimbList.add(newReimb);
			}
			return reimbList;
			
		} catch (SQLException e) {
			log.warn("Failed to retrieve all reimbursement history.");
		}
		return null;
	}

	@Override
	public boolean updateReimbStatus(Reimbursement reimb) {
		log.trace("Method called to update reimbursement.");
		log.trace("Attempting to connect to database.");
		try (Connection conn = connUtil.getConnection()) {
			log.trace("Connection successful.");

			// Update the reimbursement status
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE reimbursement SET reimb_status_id = ?, reimb_resolver = ?, reimb_resolved = ? WHERE reimb_id = ?");
			ps.setInt(1, reimb.getStatusId());
			ps.setInt(2, reimb.getResolverId());
			ps.setTimestamp(3, reimb.getResolved());
			ps.setInt(4, reimb.getReimbId());

			int numRowsUpdated = ps.executeUpdate();
			log.trace("Updated " + numRowsUpdated + " row(s).");
			return true;
		} catch (SQLException e) {
			log.warn("SQL error. Failed to update reimbursement status.");
			return false;
		}
	}

	@Override
	public List<Reimbursement> getRequestsByStatus(int status) {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		log.trace("Method called to view All History.");
		log.trace("Attempting to connect to database.");
		try(Connection conn = connUtil.getConnection()) {
			log.trace("Connection Successful.");
			
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reimbursement WHERE reimb_status_id = ?");
			ps.setInt(1, status);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement newReimb = new Reimbursement(rs.getInt("reimb_id"), rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
				reimbList.add(newReimb);
			}
			return reimbList;
			
		} catch (SQLException e) {
			log.warn("Failed to retrieve Reimbursements by status.");
		}
		return null;
	}

	@Override
	public List<Reimbursement> getUserPending(int id) {
		List<Reimbursement> reimbList = new ArrayList<Reimbursement>();
		log.trace("Method called to get pending requests for user with id: " + id);
		log.trace("Attempting to connect to database.");
		try(Connection conn = connUtil.getConnection()) {
			log.trace("Connection Successful.");
			
			// status 0 officially means PENDING
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reimbursement WHERE reimb_author = ? AND reimb_status_id = 0");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Reimbursement newReimb = new Reimbursement(rs.getInt("reimb_id"), rs.getFloat("reimb_amount"),
						rs.getTimestamp("reimb_submitted"), rs.getTimestamp("reimb_resolved"),
						rs.getString("reimb_description"), rs.getInt("reimb_author"), rs.getInt("reimb_resolver"),
						rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));
				reimbList.add(newReimb);
			}
			return reimbList;
			
		} catch (SQLException e) {
			log.warn("Failed to retrieve user's pending reimbursements.");
		}
		return null;
	}

}
