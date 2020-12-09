package BankConsulting;

import javax.swing.*;
import java.awt.*;


public class FrameMain extends JFrame {
	private JFrame frame;
	private JTextField textField;
	
	public void FL(){
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(221, 117, 531, 36);
		frame.getContentPane().add(toolBar);
		
		textField = new JTextField();
		toolBar.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("°Ë»ö");
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 12));
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("»ðÀÔ");
		btnNewButton_1.setFont(new Font("±¼¸²", Font.BOLD, 12));
		toolBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("»èÁ¦");
		btnNewButton_2.setFont(new Font("±¼¸²", Font.BOLD, 12));
		toolBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("º¯°æ");
		btnNewButton_3.setFont(new Font("±¼¸²", Font.BOLD, 12));
		toolBar.add(btnNewButton_3);
		
		JList staff_list = new JList();
		staff_list.setBounds(23, 165, 176, 261);
		staff_list.setBackground(new Color(211, 211, 211));
		frame.getContentPane().add(staff_list);
		
		JTextArea client_detail = new JTextArea("client_detail");
		client_detail.setBounds(783, 160, 176, 266);
		client_detail.setBackground(new Color(211, 211, 211));
		frame.getContentPane().add(client_detail);
		
		JTextArea account_detail = new JTextArea();
		account_detail.setBackground(new Color(255, 182, 193));
		account_detail.setText("account_detail");
		account_detail.setBounds(23, 478, 601, 173);
		frame.getContentPane().add(account_detail);
		
		JTextArea bankbook_detail = new JTextArea();
		bankbook_detail.setBackground(new Color(255, 182, 193));
		bankbook_detail.setText("bankbook_detail");
		bankbook_detail.setBounds(636, 478, 322, 173);
		frame.getContentPane().add(bankbook_detail);
		
		JLabel staff_name = new JLabel("\uC9C1\uC6D0\uBA85");
		staff_name.setFont(new Font("±¼¸²", Font.BOLD, 15));
		staff_name.setBounds(87, 117, 50, 33);
		frame.getContentPane().add(staff_name);
		
		JLabel label = new JLabel("\uACE0\uAC1D \uC815\uBCF4");
		label.setFont(new Font("±¼¸²", Font.BOLD, 15));
		label.setBounds(839, 117, 65, 33);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\uD1B5\uC7A5 \uC815\uBCF4");
		label_1.setFont(new Font("±¼¸²", Font.BOLD, 15));
		label_1.setBounds(24, 445, 81, 23);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\uACC4\uC88C \uC815\uBCF4");
		label_2.setFont(new Font("±¼¸²", Font.BOLD, 15));
		label_2.setBounds(639, 445, 81, 23);
		frame.getContentPane().add(label_2);
		
		ImageIcon nh = new ImageIcon("logo.png");
		JLabel logo = new JLabel(nh);
		logo.setBounds(12, 16, 517, 59);
		frame.getContentPane().add(logo);
		
		JLabel name = new JLabel("20184404_ÇÑÁö¿ø");
		name.setBounds(851, 0, 107, 46);
		frame.getContentPane().add(name);
		
		JTable consulting_list = new JTable();
		consulting_list.setBackground(new Color(255, 255, 255));
		consulting_list.setBounds(231, 163, 521, 261);
		frame.getContentPane().add(consulting_list);
		
		frame.setTitle("Á÷¿øº° °³¼³ °èÁÂ ¹× °í°´ Á¤º¸ Á¶È¸");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,700);
		frame.setVisible(true);
	}
}
