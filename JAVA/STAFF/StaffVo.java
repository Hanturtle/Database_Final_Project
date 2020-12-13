package STAFF;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import BankConsulting.MainDB;

public class StaffVo {

	private Connection con;
	public Statement st=null;
	//private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public void StaffSelect(DefaultTableModel t_model) {
		con = new MainDB().connect();
		
		try {
			st = con.createStatement(); 
			rs = st.executeQuery("select SNUM, PNAME,POSITION from STAFF order by SNUM");
			//DefaultTableModel에 있는 기존 데이터 지우기
			for(int i=0;i<t_model.getRowCount();) {
				t_model.removeRow(0);
			}
			
			
			while(rs.next()) {
				Object data[] = {rs.getInt(1),rs.getString(2),rs.getString(3)};
				//int snum, String pnum, String name, String position, String ph
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
