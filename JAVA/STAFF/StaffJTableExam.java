package STAFF;
import STAFF.*;
import BankConsulting.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StaffJTableExam extends JFrame implements ActionListener{
	
	
	JLabel staff_info = new JLabel("\uC9C1\uC6D0 \uC815\uBCF4");
	JToolBar S_toolBar = new JToolBar();
	JTextField S_textField = new JTextField();

	JButton S_SearchButton = new JButton("\uC870\uD68C");
	JButton S_AddButton = new JButton("\uC0BD\uC785");
	JButton S_UpdateButton = new JButton("\uBCC0\uACBD");
	JButton S_DeleteButton = new JButton("\uC0AD\uC81C");
	
	
	String[] name = {"���� �ڵ�","�̸�","����"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	StaffDAO dao = new StaffDAO();
	StaffVo vo = new StaffVo();
	StaffDelete delete = new StaffDelete();
	FrameMain fMain = new FrameMain();
	
	//������ �Լ�
	public StaffJTableExam(){

		//toolbar�� ��ư �߰�
		S_textField.setColumns(10);
		S_toolBar.add(S_textField);
		S_SearchButton.setFont(new Font("����", Font.BOLD, 13));
		S_toolBar.add(S_SearchButton);
		S_AddButton.setFont(new Font("����", Font.BOLD, 13));
		S_toolBar.add(S_AddButton);
		S_UpdateButton.setFont(new Font("����", Font.BOLD, 13));
		S_toolBar.add(S_UpdateButton);
		S_DeleteButton.setFont(new Font("����", Font.BOLD, 13));
		S_toolBar.add(S_DeleteButton);
		
		//toolbar�� frame�� �߰�
		S_toolBar.setBounds(34, 142, 236, 36);
		fMain.frame.getContentPane().add(S_toolBar);
		
		//jsp.setBackground(Color.ORANGE);
		jsp.setBounds(34, 188, 236, 180);
		fMain.frame.getContentPane().add(jsp);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//button �̺�Ʈ 
		S_SearchButton.addActionListener(this);
		S_AddButton.addActionListener(this);
		S_UpdateButton.addActionListener(this);
		S_DeleteButton.addActionListener(this);
		
		vo.StaffSelect(dt);

		
		if(dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);//ù��° �� ����
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == S_SearchButton) {
			//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
			vo.StaffSelect(dt);
			
			if(dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);//ù��° �� ����
		}
		else if(e.getSource() == S_AddButton) {
			new StaffFrame(this,"���");
		}
		else if(e.getSource() == S_UpdateButton) {
			new StaffFrame(this,"����");
		}
		else if(e.getSource() == S_DeleteButton) {
			//���õ� ��� ���� ���� ���´�
			int row = jt.getSelectedRow();
			System.out.println("������ : "+row);
			
			Object obj = jt.getValueAt(row, 0);
			System.out.println("�� : "+obj);
			
			if(delete.StaffDelete(obj.toString())>0) {
				StaffFrame.messageBox(this, "���ڵ� ������");
				
				//���̺� ����
				vo.StaffSelect(dt);
				
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				StaffFrame.messageBox(this, "���ڵ� ���� �ȵ�");
			}
		}
	}

}
