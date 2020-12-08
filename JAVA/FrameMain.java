package BankConsulting;

import javax.swing.*;
import java.awt.*;


public class FrameMain extends JFrame {
	private JFrame frame;
	private JTextField textField;
	
	public void FL(){
		
		frame = new JFrame();
		frame.setBounds(100, 100, 609, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(126, 8, 330, 23);
		frame.getContentPane().add(toolBar);
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("검색");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("삽입");
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("삭제");
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("변경");
		toolBar.add(btnNewButton_3);
		
		JList list = new JList();
		list.setBounds(12, 33, 99, 289);
		list.setBackground(Color.CYAN);
		frame.getContentPane().add(list);
		
		JTextArea client_info = new JTextArea("client_info");
		client_info.setBounds(468, 33, 113, 289);
		client_info.setBackground(Color.GRAY);
		frame.getContentPane().add(client_info);
		
		JList list_1 = new JList();
		list_1.setBounds(126, 41, 330, 281);
		list_1.setBackground(Color.YELLOW);
		frame.getContentPane().add(list_1);
		
		JTextArea bankbook_info = new JTextArea();
		bankbook_info.setText("bankbook_info");
		bankbook_info.setBounds(10, 357, 237, 126);
		frame.getContentPane().add(bankbook_info);
		
		JTextArea account_info = new JTextArea();
		account_info.setText("account_info");
		account_info.setBounds(259, 357, 322, 126);
		frame.getContentPane().add(account_info);
		
		JTextField staff_name = new JTextField();
		staff_name.setText("직원명");
		staff_name.setBounds(12, 8, 99, 21);
		frame.getContentPane().add(staff_name);
		staff_name.setColumns(10);
		
		JTextField client_name = new JTextField();
		client_name.setText("고객 정보");
		client_name.setBounds(468, 8, 116, 21);
		frame.getContentPane().add(client_name);
		client_name.setColumns(10);
		
		JTextField bankbook = new JTextField();
		bankbook.setText("통장 정보");
		bankbook.setBounds(12, 332, 116, 21);
		frame.getContentPane().add(bankbook);
		bankbook.setColumns(10);
		
		JTextField account = new JTextField();
		account.setText("계좌 정보");
		account.setBounds(259, 332, 116, 21);
		frame.getContentPane().add(account);
		account.setColumns(10);
		
		frame.setTitle("직원별 개설 계좌 및 고객 정보 조회");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,400);
		frame.setVisible(true);
	}
}
