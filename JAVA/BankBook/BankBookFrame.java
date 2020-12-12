package BankBook;

import BankBook.*;
import BankConsulting.FrameMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BankBookFrame extends JDialog implements ActionListener {

	JPanel pw = new JPanel(new GridLayout(5, 1)); //label
	JPanel pc = new JPanel(new GridLayout(5, 1));  //textfield
	JPanel ps = new JPanel();  //button

	JLabel lable_bbcode = new JLabel("코드");
	JLabel lable_bbkind = new JLabel("종류");
	JLabel lable_bbname = new JLabel("이름");
	JLabel lable_bbpercent = new JLabel("이율");
	JLabel label_bblimit = new JLabel("한도");

	JTextField code = new JTextField();
	JTextField kind = new JTextField();
	JTextField name = new JTextField();
	JTextField percent = new JTextField();
	JTextField limit = new JTextField();

	JButton confirm;
	JButton reset = new JButton("취소");

	BankBookJTableExam bb;

	JPanel codeCkP = new JPanel(new BorderLayout());
	JButton codeCkB = new JButton("코드 중복 검사");

	BankBookDAO dao = new BankBookDAO();
	BankBookInsert insert = new BankBookInsert();
	BankBookDelete delete = new BankBookDelete();
	BankBookUpdate update = new BankBookUpdate();
	BankBookVo vo = new BankBookVo();
	
	//생성자 함수
	public BankBookFrame(BankBookJTableExam bb, String index) {
		super(bb, "통장 종류 정보");
		this.bb = bb;

		// 등록과수정 버튼 중 무엇을 띄울지 정하기
		if (index.contentEquals("등록")) {
			confirm = new JButton(index);
		} 
		else {
			confirm = new JButton("수정");

			// text박스에 선택된 레코드의 정보 넣기
			// int bbcode, String bbkind, String bbname, int percent, int limit
			int row = bb.jt.getSelectedRow();
			code.setText(bb.jt.getValueAt(row, 0).toString());
			kind.setText(bb.jt.getValueAt(row, 1).toString());
			name.setText(bb.jt.getValueAt(row, 2).toString());
			percent.setText(bb.jt.getValueAt(row, 3).toString());
			limit.setText(bb.jt.getValueAt(row, 4).toString());

			// code 관련 활성화/비활성화
			// code text 박스 비활성화
			code.setEditable(false);
			// codeCheck버튼 비활성화
			codeCkB.setEnabled(false);
		}
		
		//label 추가하기
		pw.add(lable_bbcode);
        pw.add(lable_bbkind);
        pw.add(lable_bbname);
        pw.add(lable_bbpercent);
        pw.add(label_bblimit);
        
        codeCkP.add(code,"Center");
        codeCkP.add(codeCkB,"East");
        
        //TextField 추가
        pc.add(code);
        pc.add(kind);
        pc.add(name);
        pc.add(percent);
        pc.add(limit);

        //button 추가
        ps.add(confirm);
        ps.add(reset);
        
        //panel 추가
        add(pw,"West");
        add(pc,"Center");
        add(ps,"South");
        
        setSize(300,250);
        setVisible(true);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        //버튼 이벤트 등록
        confirm.addActionListener(this); //가입과 수정 이벤트
        reset.addActionListener(this);  //취소 이벤트
        codeCkB.addActionListener(this);  //code 중복 체크 이벤트
	}

	
	//버튼 이벤트 기능 구현
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//이벤트 주체에 대한 label 가져오기
		String btnLabel = e.getActionCommand();
		
		if(btnLabel.contentEquals("등록")) {
			if(insert.bankbookInsert(this) > 0) {
				messageBox(this,name.getText()+" 통장 등록 성공");
				dispose();
				
				//모든 레코드 가져와서 DefaultTableModel에 올리기
				vo.bankbookSelect(bb.dt);
				
				if(bb.dt.getRowCount() > 0)
					bb.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			}
			else {
				messageBox(this,"등록되지 않음");
			}
		}
		else if(btnLabel.equals("수정")) {
			if(update.bankbookUpdate(this)>0) {
				messageBox(this,"수정 완료");
				dispose();
				
				//모든 레코드 가져와서 DefaultTableModel에 올리기
				vo.bankbookSelect(bb.dt);
				
				if(bb.dt.getRowCount() > 0)
					bb.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			}
			else {
				messageBox(this,"수정되지 않음");
			}
		}
		else if(btnLabel.equals("취소")) {
			dispose();
		}
		else if(btnLabel.equals("코드 중복 검사")) {
			//code 박스에 값이 없으면 메시지 출력 있으면 db 연동
			if(code.getText().trim().equals("")) {
				messageBox(this,"코드 입력하세요");
				code.requestFocus(); //포커스 이동
			}
			else {
				if(dao.getCodebycheck(code.getText())) {
					messageBox(this,code.getText()+"는 사용 가능");
				}
				else {
					messageBox(this,code.getText()+"는 중복");
					
					code.setText(""); //코드에 들어간 내용 지우고
					code.requestFocus();  //커서 여기에 놓기
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}
