package BankBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class BankBookDelete {
	private Connection con;
	private PreparedStatement ps = null;
	MainDB mDB;
	
	public int bankbookDelete(String bbcode) {
		int result = 0;
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement("delete BANKBOOK where BBCODE = ?");
			ps.setString(1, bbcode.trim());
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
