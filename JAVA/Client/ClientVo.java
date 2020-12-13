package Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import BankConsulting.MainDB;

public class ClientVo {

	private Connection con;
	public Statement st=null;
	//private PreparedStatement ps = null;
	private ResultSet rs = null;
	MainDB mDB;
	
	
	public void clientSelect(DefaultTableModel t_model) {
		con = new MainDB().connect();
		
		try {
			st = con.createStatement(); 
			rs = st.executeQuery("select CNUM, PNAME,PERSON_NUM from CLIENT order by CNUM");
			//DefaultTableModel에 있는 기존 데이터 지우기
			for(int i=0;i<t_model.getRowCount();) {
				t_model.removeRow(0);
			}
			
			
			while(rs.next()) {
				Object data[] = {rs.getInt(1),rs.getString(2),rs.getString(3)};
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
