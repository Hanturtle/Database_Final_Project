package BankConsulting;
import BankConsulting.FrameMain;

import java.io.*;
import java.lang.*;
import java.sql.*;

public class MainDB {
	private Connection conn = null;

	public void connect() {
		try {
			//DB 연동
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jiwon_homework", "4404");
			System.out.println("성공적으로 접속하였음");
			
			FrameMain mainF = new FrameMain();
			mainF.FL();
			
			
			//테이블 출력문
			/*Statement stmt = conn.createStatement(); // Statement 생성
			ResultSet rset = stmt.executeQuery("select * from BANK"); // 쿼리문 수행
			System.out.println("은행코드\t은행명\t주소");
			while (rset.next()) {
				for (int i = 1; i < 4; i++) {
					System.out.print(rset.getString(i) + "\t");
				}
				System.out.println();
			}*/
		} 
		catch (ClassNotFoundException e) {
			System.out.println("해당드라이버를찾을수없습니다.\n" + e);
		} 
		catch (SQLException e) {
			System.out.println("해당드라이버를찾을수없습니다.\n" + e);
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainDB().connect();
	}

}
