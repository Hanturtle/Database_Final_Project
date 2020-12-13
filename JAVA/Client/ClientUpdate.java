package Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class ClientUpdate {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public int clientUpdate(ClientFrame client) {
		int result =0;
		String sql = "UPDATE CLIENT SET PERSON_NUM =?, PNAME =?, PHONE=? WHERE CNUM=?";
		
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement(sql);

			ps.setString(1, client.pnum.getText());
			ps.setString(2,client.name.getText());
			ps.setString(3,client.ph.getText());
			ps.setInt(4, Integer.parseInt(client.cnum.getText()));
			
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
