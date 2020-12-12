package BankBook;
import BankConsulting.*;

import java.sql.*;



public class BankBookDAO {
	//db 연결 관련
	//private Connection conn;
	public PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	//통장 종류 관련
	/*private String BBName[]= {"코드","종류","이름","이율","한도"};
	private DefaultTableModel  BBmodel = new DefaultTableModel(BBName,0);
	//private JTable table = FrameMain().BB_table;
	private String row[] = new String[5];*/
	
	
	//db 연결
	/*public BankBookDAO() {
		conn = new MainDB().connect();
	}*/
	
	public boolean getCodebycheck(String code) {
		boolean result = true;
		
		try {
			ps = mDB.conn.prepareStatement("select * from BANKBOOK where code =?");
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
