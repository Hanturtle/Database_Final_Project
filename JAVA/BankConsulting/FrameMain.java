package BankConsulting;

import BankConsulting.MainDB;
import Client.*;
import BankBook.*;
import Account.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import STAFF.*;

public class FrameMain extends JFrame {

	public static JFrame frame;
	public JTextField Account_textField;
	public JTable table;
	public JTable S_table;
	public JTextField S_textField;
	public JTable C_table;
	public JTextField C_textField;
	public JTextField BB_textField;
	public JTable BB_table;
	public JButton BB_SearchButton;
	public JToolBar BB_toolBar;
	
	

	public void FL() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		ImageIcon nh = new ImageIcon("logo.png");
		JLabel logo = new JLabel(nh);
		logo.setBounds(12, 16, 517, 59);
		frame.getContentPane().add(logo);

		JLabel name = new JLabel("20184404_ÇÑÁö¿ø");
		name.setBounds(851, 29, 107, 46);
		frame.getContentPane().add(name);

		JButton search_button = new JButton("°Ë»ö");
		search_button.setFont(new Font("±¼¸²", Font.BOLD, 12));

		//Á÷¿ø Á¤º¸
		new StaffJTableExam();
		JLabel staff_info = new JLabel("\uC9C1\uC6D0 \uC815\uBCF4");

		staff_info.setFont(new Font("±¼¸²", Font.BOLD, 15));
		staff_info.setBounds(41, 103, 90, 33);
		frame.getContentPane().add(staff_info);

		/*JToolBar S_toolBar = new JToolBar();
		S_toolBar.setBounds(34, 142, 236, 36);
		frame.getContentPane().add(S_toolBar);

		S_textField = new JTextField();
		S_toolBar.add(S_textField);
		S_textField.setColumns(10);

		JButton S_SearchButton = new JButton("\uC870\uD68C");
		S_SearchButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		S_toolBar.add(S_SearchButton);

		JButton S_AddButton = new JButton("\uC0BD\uC785");
		S_AddButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		S_toolBar.add(S_AddButton);

		JButton S_UpdateButton = new JButton("\uBCC0\uACBD");
		S_UpdateButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		S_toolBar.add(S_UpdateButton);

		JButton S_DeleteButton = new JButton("\uC0AD\uC81C");
		S_DeleteButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		S_toolBar.add(S_DeleteButton);

		S_table = new JTable();
		S_table.setBackground(Color.BLUE);
		S_table.setBounds(34, 188, 236, 180);
		frame.getContentPane().add(S_table);*/

		
		//°í°´ Á¤º¸
		new ClientJTableExam();
		JLabel client_info = new JLabel("\uACE0\uAC1D \uC815\uBCF4");
		client_info.setFont(new Font("±¼¸²", Font.BOLD, 15));
		client_info.setBounds(304, 108, 81, 23);
		frame.getContentPane().add(client_info);

		/*JToolBar C_toolBar = new JToolBar();
		C_toolBar.setBounds(303, 142, 265, 36);
		frame.getContentPane().add(C_toolBar);

		C_textField = new JTextField();
		C_textField.setColumns(10);
		C_toolBar.add(C_textField);

		JButton C_SearchButton = new JButton("\uC870\uD68C");
		C_SearchButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		C_toolBar.add(C_SearchButton);

		JButton C_AddButton = new JButton("\uC0BD\uC785");
		C_AddButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		C_toolBar.add(C_AddButton);

		JButton C_UpdateButton = new JButton("\uBCC0\uACBD");
		C_UpdateButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		C_toolBar.add(C_UpdateButton);

		JButton C_DeleteButton = new JButton("\uC0AD\uC81C");
		C_DeleteButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		C_toolBar.add(C_DeleteButton);

		C_table = new JTable();
		C_table.setBackground(Color.GREEN);
		C_table.setBounds(303, 188, 265, 180);
		frame.getContentPane().add(C_table);*/

		JLabel book_info = new JLabel("\uD1B5\uC7A5 \uC815\uBCF4");
		book_info.setFont(new Font("±¼¸²", Font.BOLD, 15));
		book_info.setBounds(590, 103, 65, 33);
		frame.getContentPane().add(book_info);

		// ÅëÀå°ü·Ã
		new BankBookJTableExam();
		/*BB_toolBar = new JToolBar();
		BB_toolBar.setBounds(590, 146, 368, 36);
		frame.getContentPane().add(BB_toolBar);

		BB_textField = new JTextField();
		BB_textField.setColumns(10);
		BB_toolBar.add(BB_textField);

		
		
		JButton BB_SearchButton = new JButton("\uC870\uD68C");
		BB_SearchButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		BB_toolBar.add(BB_SearchButton);



		JButton BB_AddButton = new JButton("\uC0BD\uC785");
		BB_AddButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		BB_toolBar.add(BB_AddButton); 

		JButton BB_UpdateButton = new JButton("\uBCC0\uACBD");
		BB_UpdateButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		BB_toolBar.add(BB_UpdateButton);

		JButton BB_DeleteButton = new JButton("\uC0AD\uC81C");
		BB_DeleteButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		BB_toolBar.add(BB_DeleteButton);*/

		/*BB_table = new JTable();
		BB_table.setBackground(Color.ORANGE);
		BB_table.setBounds(590, 188, 368, 180);
		frame.getContentPane().add(BB_table);*/

		
		//°èÁÂ °ü·Ã Á¤º¸
		new AccountJTableExam();
		JLabel account_info = new JLabel("\uACC4\uC88C \uC815\uBCF4");
		account_info.setBounds(51, 397, 96, 31);
		frame.getContentPane().add(account_info);
		account_info.setFont(new Font("±¼¸²", Font.BOLD, 20));

		/*JToolBar Account_toolBar = new JToolBar();
		Account_toolBar.setBounds(427, 397, 531, 36);
		frame.getContentPane().add(Account_toolBar);

		Account_textField = new JTextField();
		Account_toolBar.add(Account_textField);
		Account_textField.setColumns(10);

		JButton AC_SearchButton = new JButton("\uC870\uD68C");
		AC_SearchButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		Account_toolBar.add(AC_SearchButton);

		JButton AC_AddButton = new JButton("»ðÀÔ");
		AC_AddButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		Account_toolBar.add(AC_AddButton);

		JButton AC_UpdateButton = new JButton("º¯°æ");
		AC_UpdateButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		Account_toolBar.add(AC_UpdateButton);

		JButton AC_DeleteButton = new JButton("»èÁ¦");
		AC_DeleteButton.setFont(new Font("±¼¸²", Font.BOLD, 13));
		Account_toolBar.add(AC_DeleteButton);

		JTable AC_table = new JTable();
		AC_table.setBackground(Color.MAGENTA);
		AC_table.setBounds(34, 443, 925, 208);
		frame.getContentPane().add(AC_table);*/

		frame.setTitle("Á÷¿øº° °³¼³ °èÁÂ ¹× °í°´ Á¤º¸ Á¶È¸");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setVisible(true);
	}
}
