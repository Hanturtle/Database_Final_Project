package BankConsulting;
import BankConsulting.FrameMain;

import java.io.*;
import java.lang.*;
import java.sql.*;

public class MainDB {
	private Connection conn = null;

	public void connect() {
		try {
			//DB ����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jiwon_homework", "4404");
			System.out.println("���������� �����Ͽ���");
			
			FrameMain mainF = new FrameMain();
			mainF.FL();
			
			
			//���̺� ��¹�
			/*Statement stmt = conn.createStatement(); // Statement ����
			ResultSet rset = stmt.executeQuery("select * from BANK"); // ������ ����
			System.out.println("�����ڵ�\t�����\t�ּ�");
			while (rset.next()) {
				for (int i = 1; i < 4; i++) {
					System.out.print(rset.getString(i) + "\t");
				}
				System.out.println();
			}*/
		} 
		catch (ClassNotFoundException e) {
			System.out.println("�ش����̹���ã���������ϴ�.\n" + e);
		} 
		catch (SQLException e) {
			System.out.println("�ش����̹���ã���������ϴ�.\n" + e);
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainDB().connect();
	}

}
