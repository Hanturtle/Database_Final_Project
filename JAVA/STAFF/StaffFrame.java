package STAFF;

import BankBook.*;
import BankConsulting.FrameMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class StaffFrame extends JDialog implements ActionListener {

	JPanel pw = new JPanel(new GridLayout(5, 1)); //label
	JPanel pc = new JPanel(new GridLayout(5, 1));  //textfield
	JPanel ps = new JPanel();  //button

	JLabel lable_snum = new JLabel("직원 코드");
	JLabel lable_pnum = new JLabel("주민번호");
	JLabel lable_name = new JLabel("이름");
	JLabel lable_position = new JLabel("직급");
	JLabel label_ph = new JLabel("전화번호");

	JTextField snum = new JTextField();
	JTextField pnum = new JTextField();
	JTextField name = new JTextField();
	JTextField position = new JTextField();
	JTextField ph = new JTextField();

	JButton confirm;
	JButton reset = new JButton("취소");

	StaffJTableExam s;

	JPanel codeCkP = new JPanel(new BorderLayout());
	JButton codeCkB = new JButton("코드 중복 검사");

	StaffDAO dao = new StaffDAO();
	StaffInsert insert = new StaffInsert();
	StaffDelete delete = new StaffDelete();
	StaffUpdate update = new StaffUpdate();
	StaffVo vo = new StaffVo();
	
	//생성자 함수
	public StaffFrame(StaffJTableExam s, String index) {
		super(s, "직원 정보");
		this.s = s;

		// 등록과수정 버튼 중 무엇을 띄울지 정하기
		if (index.contentEquals("등록")) {
			confirm = new JButton(index);
		} 
		else {
			confirm = new JButton("수정");

			// text박스에 선택된 레코드의 정보 넣기
			int row = s.jt.getSelectedRow();
			snum.setText(s.jt.getValueAt(row, 0).toString());
			//pnum.setText(s.jt.getValueAt(row, 1).toString());
			name.setText(s.jt.getValueAt(row, 1).toString());
			position.setText(s.jt.getValueAt(row, 2).toString());
			//ph.setText(s.jt.getValueAt(row, 4).toString());

			// code 관련 활성화/비활성화
			// code text 박스 비활성화
			snum.setEditable(false);
			// codeCheck버튼 비활성화
			codeCkB.setEnabled(false);
		}
		
		//label 추가하기
		pw.add(lable_snum);
        pw.add(lable_pnum);
        pw.add(lable_name);
        pw.add(lable_position);
        pw.add(label_ph);
        
        codeCkP.add(snum,"Center");
        codeCkP.add(codeCkB,"East");
        
        //TextField 추가
        pc.add(snum);
        pc.add(pnum);
        pc.add(name);
        pc.add(position);
        pc.add(ph);

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
			if(insert.StaffInsert(this) > 0) {
				messageBox(this,name.getText()+" 직원 등록 성공");
				dispose();
				
				//모든 레코드 가져와서 DefaultTableModel에 올리기
				vo.StaffSelect(s.dt);
				
				if(s.dt.getRowCount() > 0)
					s.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			}
			else {
				messageBox(this,"등록되지 않음");
			}
		}
		else if(btnLabel.equals("수정")) {
			if(update.StaffUpdate(this)>0) {
				messageBox(this,"수정 완료");
				dispose();
				
				//모든 레코드 가져와서 DefaultTableModel에 올리기
				vo.StaffSelect(s.dt);
				
				if(s.dt.getRowCount() > 0)
					s.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
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
			if(snum.getText().trim().equals("")) {
				messageBox(this,"코드 입력하세요");
				snum.requestFocus(); //포커스 이동
			}
			else {
				if(dao.getCodebycheck(snum.getText())) {
					messageBox(this,snum.getText()+"는 사용 가능");
				}
				else {
					messageBox(this,snum.getText()+"는 중복");
					
					snum.setText(""); //코드에 들어간 내용 지우고
					snum.requestFocus();  //커서 여기에 놓기
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}
