package Account;
import Account.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankBook.*;
import BankConsulting.MainDB;

public class AccountInsert {


	private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	public int accountInsert(AccountFrame account) {
		int result = 0;
		
		con = new MainDB().connect();
		try {
			//int bbcode, String bbkind, String bbname, int percent, int limit
			ps = con.prepareStatement("insert into ACCOUNT values(?,?,?,?,?,?)");
			ps.setString(1, account.acnum.getText());
			ps.setInt(2,Integer.parseInt(account.cnum.getText()));
			ps.setInt(3,Integer.parseInt(account.snum.getText()));
			ps.setInt(4,Integer.parseInt(account.bbcode.getText()));
			ps.setString(5, account.day.getText());
			ps.setInt(6, Integer.parseInt(account.money.getText()));
			
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
