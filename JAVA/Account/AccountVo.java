package Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import BankConsulting.MainDB;

public class AccountVo {

	private Connection con;
	public Statement st=null;
	//private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public void accountSelect(DefaultTableModel t_model) {
		con = new MainDB().connect();
		
		try {
			st = con.createStatement(); 
			rs = st.executeQuery("select * from ACCOUNT order by BBCODE");
			//DefaultTableModel에 있는 기존 데이터 지우기
			for(int i=0;i<t_model.getRowCount();) {
				t_model.removeRow(0);
			}
			
			
			while(rs.next()) {
				Object data[] = {rs.getString(1),rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getInt(6)};
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
