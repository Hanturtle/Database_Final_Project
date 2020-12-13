package STAFF;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class StaffDelete {
	private Connection con;
	private PreparedStatement ps = null;
	MainDB mDB;
	
	public int StaffDelete(String snum) {
		int result = 0;
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement("delete STAFF where SNUM = ?");
			ps.setString(1, snum.trim());
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
