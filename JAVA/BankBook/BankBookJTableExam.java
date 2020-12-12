package BankBook;
import BankBook.*;
import BankConsulting.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BankBookJTableExam extends JFrame implements ActionListener{
	
	JToolBar BB_toolBar = new JToolBar();
	JTextField BB_textField = new JTextField();
	JButton BB_SearchButton = new JButton("\uC870\uD68C");
	JButton BB_AddButton = new JButton("\uC0BD\uC785");
	JButton BB_UpdateButton = new JButton("\uBCC0\uACBD");
	JButton BB_DeleteButton = new JButton("\uC0AD\uC81C");
	
	String[] name = {"코드","종류","이름","이율","한도"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	BankBookDAO dao = new BankBookDAO();
	BankBookVo vo = new BankBookVo();
	BankBookDelete delete = new BankBookDelete();
	FrameMain fMain = new FrameMain();
	
	//생성자 함수
	public BankBookJTableExam(){
		
		//toolbar에 버튼 추가
		BB_textField.setColumns(10);
		BB_toolBar.add(BB_textField);
		BB_SearchButton.setFont(new Font("굴림", Font.BOLD, 13));
		BB_toolBar.add(BB_SearchButton);
		BB_AddButton.setFont(new Font("굴림", Font.BOLD, 13));
		BB_toolBar.add(BB_AddButton); 
		BB_UpdateButton.setFont(new Font("굴림", Font.BOLD, 13));
		BB_toolBar.add(BB_UpdateButton);
		BB_DeleteButton.setFont(new Font("굴림", Font.BOLD, 13));
		BB_toolBar.add(BB_DeleteButton);
		
		//toolbar를 frame에 추가
		BB_toolBar.setBounds(590, 146, 368, 36);
		fMain.frame.getContentPane().add(BB_toolBar);
		
		jsp.setBackground(Color.ORANGE);
		jsp.setBounds(590, 188, 368, 180);
		fMain.frame.getContentPane().add(jsp);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//button 이벤트 
		BB_SearchButton.addActionListener(this);
		BB_AddButton.addActionListener(this);
		BB_UpdateButton.addActionListener(this);
		BB_DeleteButton.addActionListener(this);
		
		vo.bankbookSelect(dt);
		
		
		
		
		if(dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == BB_SearchButton) {
			//모든 레코드 가져와서 DefaultTableModel에 올리기
			vo.bankbookSelect(dt);
			
			if(dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
		}
		else if(e.getSource() == BB_AddButton) {
			new BankBookFrame(this,"등록");
		}
		else if(e.getSource() == BB_UpdateButton) {
			new BankBookFrame(this,"수정");
		}
		else if(e.getSource() == BB_DeleteButton) {
			//선택된 행과 열의 값을 얻어온다
			int row = jt.getSelectedRow();
			System.out.println("선택행 : "+row);
			
			Object obj = jt.getValueAt(row, 0);
			System.out.println("값 : "+obj);
			
			if(delete.bankbookDelete(obj.toString())>0) {
				BankBookFrame.messageBox(this, "레코드 삭제됨");
				
				//테이블 갱신
				vo.bankbookSelect(dt);
				
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			}
			else {
				BankBookFrame.messageBox(this, "레코드 삭제 안됨");
			}
		}
	}

}
