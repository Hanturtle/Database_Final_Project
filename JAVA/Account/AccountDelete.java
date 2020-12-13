package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class AccountDelete {
	private Connection con;
	private PreparedStatement ps = null;
	MainDB mDB;
	
	public int accountDelete(String acnum) {
		int result = 0;
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement("delete ACCOUNT where ACCOUNT_NUM = ?");
			ps.setString(1, acnum.trim());
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
