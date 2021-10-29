package com.projectone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projectone.models.Reimburse;
import com.projectone.utils.ConnectionUtil;

public class ReimburseDao {
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	public List<Reimburse> getAllReimbursments(){
		
		List<Reimburse> reimburselist = new ArrayList<Reimburse>();
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM reimbursements";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				reimburselist.add(new Reimburse(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6)));
			}
			return reimburselist;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Reimburse getReimburseById(int id) {
		
		Reimburse rb = new Reimburse();
		
		try {
			Connection con = conUtil.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE users.id = '"+ id +"'";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				rb.setImbursId(rs.getInt(1));
				rb.setRecipientid(rs.getInt(2));
				rb.setImbursType(rs.getString(3));
				rb.setImbursStatus(rs.getString(4));
				rb.setImbursedescription(rs.getString(5));
				rb.setImburseAmount(rs.getDouble(6));

			}
			return rb;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public void updateReimburseById(Reimburse rb) {
		
		Connection con = conUtil.getConnection();
		
		try {
			
			//still create the sql string, but with some small changes
			String sql = "UPDATE reimbursements SET imbursestatus = ? WHERE reimbursements.recipientid = users.id";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rb.getImbursStatus());
			ps.setInt(7, rb.getImbursId());
			
			ps.execute();
			
			
			
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public void createReimburse(Reimburse rb) throws SQLException {
		
	Connection con = conUtil.getConnection();
	
	//still create the sql string, but with some small changes
	String sql = "INSERT INTO reimbursements (recipientid,imbursetype,imbursestatus,imbursedecricption,imburseamount) VALUES (?,?,?,?,?)";
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, rb.getRecipientid());
	ps.setString(2, rb.getImbursType());
	ps.setString(3, rb.getImbursStatus());
	ps.setString(4, rb.getImbursedescription());
	ps.setDouble(5, rb.getImburseAmount());
	
	
	
	ps.execute();
}
	//?deleteReimburse?

}
