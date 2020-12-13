package Account;
import BankBook.*;
import BankConsulting.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AccountJTableExam extends JFrame implements ActionListener{
	
	JToolBar Account_toolBar = new JToolBar();
	JTextField Account_textField = new JTextField();

	JButton AC_SearchButton = new JButton("\uC870\uD68C");
	JButton AC_AddButton = new JButton("����");
	JButton AC_UpdateButton = new JButton("����");
	JButton AC_DeleteButton = new JButton("����");

	
	String[] name = {"���¹�ȣ","���ڵ�","�����ڵ�","�����ڵ�","�ֱ� ������","�ܾ�"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	AccountDAO dao = new AccountDAO();
	AccountVo vo = new AccountVo();
	AccountDelete delete = new AccountDelete();
	FrameMain fMain = new FrameMain();
	
	//������ �Լ�
	public AccountJTableExam(){
		
		//toolbar�� ��ư �߰�
		Account_textField.setColumns(10);
		Account_toolBar.add(Account_textField);
		AC_SearchButton.setFont(new Font("����", Font.BOLD, 13));
		Account_toolBar.add(AC_SearchButton);
		AC_AddButton.setFont(new Font("����", Font.BOLD, 13));
		Account_toolBar.add(AC_AddButton); 
		AC_UpdateButton.setFont(new Font("����", Font.BOLD, 13));
		Account_toolBar.add(AC_UpdateButton);
		AC_DeleteButton.setFont(new Font("����", Font.BOLD, 13));
		Account_toolBar.add(AC_DeleteButton);
		
		//toolbar�� frame�� �߰�
		Account_toolBar.setBounds(427, 397, 531, 36);
		fMain.frame.getContentPane().add(Account_toolBar);
		
		jsp.setBackground(Color.ORANGE);
		jsp.setBounds(34, 443, 925, 208);
		fMain.frame.getContentPane().add(jsp);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//button �̺�Ʈ 
		AC_SearchButton.addActionListener(this);
		AC_AddButton.addActionListener(this);
		AC_UpdateButton.addActionListener(this);
		AC_DeleteButton.addActionListener(this);
		
		vo.accountSelect(dt);
		
		
		
		
		if(dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);//ù��° �� ����
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == AC_SearchButton) {
			//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
			vo.accountSelect(dt);
			
			if(dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);//ù��° �� ����
		}
		else if(e.getSource() == AC_AddButton) {
			new AccountFrame(this,"���");
		}
		else if(e.getSource() == AC_UpdateButton) {
			new AccountFrame(this,"����");
		}
		else if(e.getSource() == AC_DeleteButton) {
			//���õ� ��� ���� ���� ���´�
			int row = jt.getSelectedRow();
			System.out.println("������ : "+row);
			
			Object obj = jt.getValueAt(row, 0);
			System.out.println("�� : "+obj);
			
			if(delete.accountDelete(obj.toString())>0) {
				AccountFrame.messageBox(this, "���ڵ� ������");
				
				//���̺� ����
				vo.accountSelect(dt);
				
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				AccountFrame.messageBox(this, "���ڵ� ���� �ȵ�");
			}
		}
	}

}
