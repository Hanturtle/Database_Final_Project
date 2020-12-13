package STAFF;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankBook.*;
import BankConsulting.MainDB;

public class StaffInsert {


	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	public int StaffInsert(StaffFrame staff) {
		int result = 0;
		
		con = new MainDB().connect();
		try {
			ps = con.prepareStatement("insert into STAFF values(?,?,?,?,?)");
			ps.setInt(1, Integer.parseInt(staff.snum.getText()));
			ps.setString(2, staff.pnum.getText());
			ps.setString(4,staff.name.getText());
			ps.setString(3,staff.position.getText());
			ps.setString(5,staff.ph.getText());
			
			result = ps.executeUpdate(); //실행 -> 저장
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
