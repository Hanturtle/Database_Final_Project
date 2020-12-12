package BankConsulting;

import BankConsulting.*;
import BankBook.*;

import java.io.*;
import java.lang.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainDB {
	public Connection conn = null;
	public Statement stmt = null;
	public PreparedStatement ps;
	public ResultSet rs;
	

	
	// DB 연결
	public Connection connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jiwon_homework", "4404");
			System.out.println("성공적으로 접속하였음");
			stmt = conn.createStatement();
			//new FrameMain().FL();
		} catch (ClassNotFoundException e) {
			System.out.println("해당드라이버를찾을수없습니다.\n" + e);
		} catch (SQLException e) {
			System.out.println("해당드라이버를찾을수없습니다.\n" + e);
		}
		
		return conn;
	}
	
	//DB 연결 해제
	public void dbClose() {
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
			if(ps != null)
				ps.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new MainDB().connect();
		new FrameMain().FL();
	}

}
