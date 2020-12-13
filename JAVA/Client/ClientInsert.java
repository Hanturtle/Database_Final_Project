package Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankBook.*;
import BankConsulting.MainDB;

public class ClientInsert {


	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	public int clientInsert(ClientFrame client) {
		int result = 0;
		
		con = new MainDB().connect();
		try {
			ps = con.prepareStatement("insert into CLIENT values(?,?,?,?)");
			ps.setInt(1, Integer.parseInt(client.cnum.getText()));
			ps.setString(2, client.pnum.getText());
			ps.setString(3,client.name.getText());
			ps.setString(4,client.ph.getText());
			
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
