package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class ClientDelete {
	private Connection con;
	private PreparedStatement ps = null;
	MainDB mDB;
	
	public int clientDelete(String cnum) {
		int result = 0;
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement("delete CLIENT where CNUM = ?");
			ps.setString(1, cnum.trim());
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
