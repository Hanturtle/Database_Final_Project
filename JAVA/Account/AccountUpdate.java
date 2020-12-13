package Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankConsulting.MainDB;

public class AccountUpdate {

	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public int accountUpdate(AccountFrame account) {
		int result =0;
		String sql = "UPDATE ACCOUNT SET CNUM =?, SNUM =?, BBCODE=?, CREATE_DAY=?, MONEY=? WHERE ACCOUNT_NUM=?";
		
		con = new MainDB().connect();
		
		try {
			ps = con.prepareStatement(sql);

			ps.setInt(1,Integer.parseInt(account.cnum.getText()));
			ps.setInt(2,Integer.parseInt(account.snum.getText()));
			ps.setInt(3,Integer.parseInt(account.bbcode.getText()));
			ps.setString(4, account.day.getText());
			ps.setInt(5, Integer.parseInt(account.money.getText()));
			ps.setString(6, account.acnum.getText());
			
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
