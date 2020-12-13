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
	JButton AC_AddButton = new JButton("삽입");
	JButton AC_UpdateButton = new JButton("변경");
	JButton AC_DeleteButton = new JButton("삭제");

	
	String[] name = {"계좌번호","고객코드","직원코드","통장코드","최근 수정일","잔액"};
	
	DefaultTableModel dt = new DefaultTableModel(name,0);
	JTable jt = new JTable(dt);
	JScrollPane jsp = new JScrollPane(jt);
	
	AccountDAO dao = new AccountDAO();
	AccountVo vo = new AccountVo();
	AccountDelete delete = new AccountDelete();
	FrameMain fMain = new FrameMain();
	
	//생성자 함수
	public AccountJTableExam(){
		
		//toolbar에 버튼 추가
		Account_textField.setColumns(10);
		Account_toolBar.add(Account_textField);
		AC_SearchButton.setFont(new Font("굴림", Font.BOLD, 13));
		Account_toolBar.add(AC_SearchButton);
		AC_AddButton.setFont(new Font("굴림", Font.BOLD, 13));
		Account_toolBar.add(AC_AddButton); 
		AC_UpdateButton.setFont(new Font("굴림", Font.BOLD, 13));
		Account_toolBar.add(AC_UpdateButton);
		AC_DeleteButton.setFont(new Font("굴림", Font.BOLD, 13));
		Account_toolBar.add(AC_DeleteButton);
		
		//toolbar를 frame에 추가
		Account_toolBar.setBounds(427, 397, 531, 36);
		fMain.frame.getContentPane().add(Account_toolBar);
		
		jsp.setBackground(Color.ORANGE);
		jsp.setBounds(34, 443, 925, 208);
		fMain.frame.getContentPane().add(jsp);
		
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//button 이벤트 
		AC_SearchButton.addActionListener(this);
		AC_AddButton.addActionListener(this);
		AC_UpdateButton.addActionListener(this);
		AC_DeleteButton.addActionListener(this);
		
		vo.accountSelect(dt);
		
		
		
		
		if(dt.getRowCount() > 0)
			jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == AC_SearchButton) {
			//모든 레코드 가져와서 DefaultTableModel에 올리기
			vo.accountSelect(dt);
			
			if(dt.getRowCount() > 0)
				jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
		}
		else if(e.getSource() == AC_AddButton) {
			new AccountFrame(this,"등록");
		}
		else if(e.getSource() == AC_UpdateButton) {
			new AccountFrame(this,"수정");
		}
		else if(e.getSource() == AC_DeleteButton) {
			//선택된 행과 열의 값을 얻어온다
			int row = jt.getSelectedRow();
			System.out.println("선택행 : "+row);
			
			Object obj = jt.getValueAt(row, 0);
			System.out.println("값 : "+obj);
			
			if(delete.accountDelete(obj.toString())>0) {
				AccountFrame.messageBox(this, "레코드 삭제됨");
				
				//테이블 갱신
				vo.accountSelect(dt);
				
				if(dt.getRowCount() > 0)
					jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			}
			else {
				AccountFrame.messageBox(this, "레코드 삭제 안됨");
			}
		}
	}

}
