package BankBook;
import BankConsulting.*;

import java.sql.*;



public class BankBookDAO {
	//db ���� ����
	//private Connection conn;
	public PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	//���� ���� ����
	/*private String BBName[]= {"�ڵ�","����","�̸�","����","�ѵ�"};
	private DefaultTableModel  BBmodel = new DefaultTableModel(BBName,0);
	//private JTable table = FrameMain().BB_table;
	private String row[] = new String[5];*/
	
	
	//db ����
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
