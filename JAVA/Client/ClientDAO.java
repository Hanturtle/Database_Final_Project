package Client;
import BankConsulting.*;

import java.sql.*;



public class ClientDAO {
	//db 연결 관련
	//private Connection conn;
	public PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;

	public boolean getCodebycheck(String code) {
		boolean result = true;
		
		try {
			ps = mDB.conn.prepareStatement("select CNUM,PNAME,PERSON_NUM from CLIENT where code =?");
			ps.setString(1,code.trim());
			rs = ps.executeQuery();
			
			if(rs.next())
				result = false;
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
