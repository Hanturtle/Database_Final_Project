package Client;
import STAFF.*;
import BankConsulting.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClientJTableExam extends JFrame implements ActionListener{
	JLabel client_info = new JLabel("\uACE0\uAC1D \uC815\uBCF4");
	JToolBar C_toolBar = new JToolBar();
	JTextField C_textField = new JTextField();
	
	JButton C_SearchButton = new JButton("\uC870\uD68C");
	JButton C_AddButton = new JButton("\uC0BD\uC785");
	JButton C_UpdateButton = new JButton("\uBCC0\uACBD");
	JButton C_DeleteButton = new JButton("\uC0AD\uC81C");
	
	
	String[] name = {"�� �ڵ�","�̸�","�ֹι�ȣ"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	ClientDAO dao = new ClientDAO();
	ClientVo vo = new ClientVo();
	ClientDelete delete = new ClientDelete();
	FrameMain fMain = new FrameMain();
	
	//������ �Լ�
	public ClientJTableExam(){
		
		//toolbar�� ��ư �߰�
		C_textField.setColumns(10);
		C_toolBar.add(C_textField);
		C_SearchButton.setFont(new Font("����", Font.BOLD, 13));
		C_toolBar.add(C_SearchButton);
		C_AddButton.setFont(new Font("����", Font.BOLD, 13));
		C_toolBar.add(C_AddButton);
		C_UpdateButton.setFont(new Font("����", Font.BOLD, 13));
		C_toolBar.add(C_UpdateButton);
		C_DeleteButton.setFont(new Font("����", Font.BOLD, 13));
		C_toolBar.add(C_DeleteButton);
		
		//toolbar�� frame�� �߰�
		C_toolBar.setBounds(303, 142, 265, 36);
		fMain.frame.getContentPane().add(C_toolBar);
		
		//jsp.setBackground(Color.ORANGE);
		jsp.setBounds(303, 188, 265, 180);
		fMain.frame.getContentPane().add(jsp);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//button �̺�Ʈ 
		C_SearchButton.addActionListener(this);
		C_AddButton.addActionListener(this);
		C_UpdateButton.addActionListener(this);
		C_DeleteButton.addActionListener(this);
		
		vo.clientSelect(dt);

		
		if(dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);//ù��° �� ����
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == C_SearchButton) {
			//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
			vo.clientSelect(dt);
			
			if(dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);//ù��° �� ����
		}
		else if(e.getSource() == C_AddButton) {
			new ClientFrame(this,"���");
		}
		else if(e.getSource() == C_UpdateButton) {
			new ClientFrame(this,"����");
		}
		else if(e.getSource() == C_DeleteButton) {
			//���õ� ��� ���� ���� ���´�
			int row = jt.getSelectedRow();
			System.out.println("������ : "+row);
			
			Object obj = jt.getValueAt(row, 0);
			System.out.println("�� : "+obj);
			
			if(delete.clientDelete(obj.toString())>0) {
				ClientFrame.messageBox(this, "���ڵ� ������");
				
				//���̺� ����
				vo.clientSelect(dt);
				
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				ClientFrame.messageBox(this, "���ڵ� ���� �ȵ�");
			}
		}
	}

}
