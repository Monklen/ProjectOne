package com.projectone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projectone.models.User;
import com.projectone.utils.ConnectionUtil;

public class UserDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	//use statements to talk to the database
	
	public List<User> getAllUsers() {
		
		List<User> accountlist = new ArrayList<User>();
		
		try {
			// make the connection the db
			Connection con = conUtil.getConnection();
			
			//create a sql statement
			String sql = "SELECT * FROM users";
			
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			// we have to loop through the resultset and create objects based off the return
			while(rs.next()) {
				accountlist.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			return accountlist;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public User getUserByUsername(String username) {
		
		User u = new User();
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM users WHERE users.username = '"+ username +"'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setFirstname(rs.getString(2));
				u.setLastname(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setUsername(rs.getString(5));
				u.setPassword(rs.getString(6));
				u.setUsertype(rs.getString(7));
				
			}
			return u;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//use prepared statement to precompile the sql and protect against sql injection
	
	public void createUser(User u) throws SQLException {
		
		Connection con = conUtil.getConnection();
		
		//still create the sql string, but with some small changes
		String sql = "INSERT INTO users (firstname,lastname,email,username,password,usertype) VALUES (?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, u.getFirstname());
		ps.setString(2, u.getLastname());
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getUsername());
		ps.setString(5, u.getPassword());
		ps.setString(6, u.getUsertype());
		
		
		ps.execute();
	}

	
	public void updateUser(User u) {
		
		Connection con = conUtil.getConnection();
		
		try {
			
		//still create the sql string, but with some small changes
		String sql = "UPDATE users SET firstname = ?, lastname = ?,email = ?, username = ?, password = ?, usertype = ? WHERE users.id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getFirstname());
		ps.setString(2, u.getLastname());
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getUsername());
		ps.setString(5, u.getPassword());
		ps.setString(6, u.getUsertype());
		ps.setInt(7, u.getId());
		
		ps.execute();
		
		
		
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	} 
	
	
	public void deleteUser(User u) {
		try {
		Connection con = conUtil.getConnection();
		
		String sql = "DELETE FROM users WHERE accountholders.id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, u.getId());
		ps.execute();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public void updateBalance(User u) {
//		try {
//			Connection con = conUtil.getConnection();
//	
//			//still create the sql string, but with some small changes
//			String sql = "UPDATE users SET balance = ? WHERE accountholders.id = ?;";
//			PreparedStatement ps = con.prepareStatement(sql);
//			ps.setDouble(1, u.getBalance());
//			ps.setInt(2, u.getId());
//			
//			ps.executeUpdate();
//		}
//		catch(SQLException e) {
//		e.printStackTrace();
//		}
//	}
	
 

}
