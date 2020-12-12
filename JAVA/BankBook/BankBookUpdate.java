package BankBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class BankBookUpdate {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public int bankbookUpdate(BankBookFrame bankbook) {
		int result =0;
		String sql = "UPDATE BANKBOOK SET BBKIND =?, BBNAME =?, PERCENT=?, LIMIT=? WHERE BBCODE=?";
		
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, bankbook.kind.getText());
			ps.setString(2,bankbook.name.getText());
			ps.setInt(3,Integer.parseInt(bankbook.percent.getText()));
			ps.setInt(4, Integer.parseInt(bankbook.limit.getText()));
			ps.setInt(5, Integer.parseInt(bankbook.code.getText()));
			
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
