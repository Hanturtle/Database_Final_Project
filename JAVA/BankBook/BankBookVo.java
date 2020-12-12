package BankBook;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import BankConsulting.MainDB;

public class BankBookVo {

	private Connection con;
	public Statement st=null;
	//private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public void bankbookSelect(DefaultTableModel t_model) {
		con = new MainDB().connect();
		
		try {
			st = con.createStatement(); 
			rs = st.executeQuery("select * from BANKBOOK order by BBCODE");
			//DefaultTableModel에 있는 기존 데이터 지우기
			for(int i=0;i<t_model.getRowCount();) {
				t_model.removeRow(0);
			}
			
			
			while(rs.next()) {
				Object data[] = {rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)};
				//Object data[] = {mDB.rs.getInt(1),mDB.rs.getShort(2),mDB.rs.getString(3),mDB.rs.getInt(4),mDB.rs.getInt(5)};
				t_model.addRow(data);
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		finally {
			new MainDB().dbClose();
		}
	}
}
