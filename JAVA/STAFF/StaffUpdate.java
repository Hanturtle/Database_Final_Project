package STAFF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class StaffUpdate {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public int StaffUpdate(StaffFrame staff) {
		int result =0;
		String sql = "UPDATE STAFF SET PERSON_NUM =?, PNAME =?, POSITION =?, PHONE=? WHERE SNUM=?";
		
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement(sql);
			//int snum, String pnum, String name, String position, String ph
			
			ps.setString(1, staff.pnum.getText());
			ps.setString(2,staff.name.getText());
			ps.setString(3,staff.position.getText());
			ps.setString(4,staff.ph.getText());
			ps.setInt(5, Integer.parseInt(staff.snum.getText()));
			
			result = ps.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		finally {
			new MainDB().dbClose();
		}
		
		return result;
		
	}
}
