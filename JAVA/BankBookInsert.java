package BankBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BankBook.*;
import BankConsulting.MainDB;

public class BankBookInsert {


	//private Connection con;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	public int bankbookInsert(BankBookFrame bankbook) {
		int result = 0;
		
		//con = new MainDB().connect();
		try {
			//int bbcode, String bbkind, String bbname, int percent, int limit
			ps = mDB.conn.prepareStatement("insert into BANKBOOK values(?,?,?,?,?");
			ps.setInt(1, Integer.parseInt(bankbook.code.getText()));
			ps.setString(2, bankbook.kind.getText());
			ps.setString(3,bankbook.name.getText());
			ps.setInt(4,Integer.parseInt(bankbook.percent.getText()));
			ps.setInt(5, Integer.parseInt(bankbook.limit.getText()));
			
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
