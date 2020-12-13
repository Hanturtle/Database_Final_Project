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
	
	
	String[] name = {"고객 코드","이름","주민번호"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	ClientDAO dao = new ClientDAO();
	ClientVo vo = new ClientVo();
	ClientDelete delete = new ClientDelete();
	FrameMain fMain = new FrameMain();
	
	//생성자 함수
	public ClientJTableExam(){
		
		//toolbar에 버튼 추가
		C_textField.setColumns(10);
		C_toolBar.add(C_textField);
		C_SearchButton.setFont(new Font("굴림", Font.BOLD, 13));
		C_toolBar.add(C_SearchButton);
		C_AddButton.setFont(new Font("굴림", Font.BOLD, 13));
		C_toolBar.add(C_AddButton);
		C_UpdateButton.setFont(new Font("굴림", Font.BOLD, 13));
		C_toolBar.add(C_UpdateButton);
		C_DeleteButton.setFont(new Font("굴림", Font.BOLD, 13));
		C_toolBar.add(C_DeleteButton);
		
		//toolbar를 frame에 추가
		C_toolBar.setBounds(303, 142, 265, 36);
		fMain.frame.getContentPane().add(C_toolBar);
		
		//jsp.setBackground(Color.ORANGE);
		jsp.setBounds(303, 188, 265, 180);
		fMain.frame.getContentPane().add(jsp);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//button 이벤트 
		C_SearchButton.addActionListener(this);
		C_AddButton.addActionListener(this);
		C_UpdateButton.addActionListener(this);
		C_DeleteButton.addActionListener(this);
		
		vo.clientSelect(dt);

		
		if(dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == C_SearchButton) {
			//모든 레코드 가져와서 DefaultTableModel에 올리기
			vo.clientSelect(dt);
			
			if(dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
		}
		else if(e.getSource() == C_AddButton) {
			new ClientFrame(this,"등록");
		}
		else if(e.getSource() == C_UpdateButton) {
			new ClientFrame(this,"수정");
		}
		else if(e.getSource() == C_DeleteButton) {
			//선택된 행과 열의 값을 얻어온다
			int row = jt.getSelectedRow();
			System.out.println("선택행 : "+row);
			
			Object obj = jt.getValueAt(row, 0);
			System.out.println("값 : "+obj);
			
			if(delete.clientDelete(obj.toString())>0) {
				ClientFrame.messageBox(this, "레코드 삭제됨");
				
				//테이블 갱신
				vo.clientSelect(dt);
				
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			}
			else {
				ClientFrame.messageBox(this, "레코드 삭제 안됨");
			}
		}
	}

}
