package Account;

import BankBook.*;
import BankConsulting.FrameMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AccountFrame extends JDialog implements ActionListener {

	JPanel pw = new JPanel(new GridLayout(6, 1)); //label
	JPanel pc = new JPanel(new GridLayout(6, 1));  //textfield
	JPanel ps = new JPanel();  //button

	JLabel lable_acnum = new JLabel("계좌번호");
	JLabel lable_cnum = new JLabel("고객코드");
	JLabel lable_snum = new JLabel("직원코드");
	JLabel lable_bbcode = new JLabel("통장코드");
	JLabel label_day = new JLabel("최근 수정일");
	JLabel label_money = new JLabel("잔액");

	JTextField acnum = new JTextField();
	JTextField cnum = new JTextField();
	JTextField snum = new JTextField();
	JTextField bbcode = new JTextField();
	JTextField day = new JTextField();
	JTextField money = new JTextField();

	JButton confirm;
	JButton reset = new JButton("취소");

	AccountJTableExam ac;

	JPanel codeCkP = new JPanel(new BorderLayout());
	JButton codeCkB = new JButton("코드 중복 검사");

	AccountDAO dao = new AccountDAO();
	AccountInsert insert = new AccountInsert();
	AccountDelete delete = new AccountDelete();
	AccountUpdate update = new AccountUpdate();
	AccountVo vo = new AccountVo();
	
	//생성자 함수
	public AccountFrame(AccountJTableExam ac, String index) {
		super(ac, "통장 종류 정보");
		this.ac = ac;

		// 등록과수정 버튼 중 무엇을 띄울지 정하기
		if (index.contentEquals("등록")) {
			confirm = new JButton(index);
		} 
		else {
			confirm = new JButton("수정");

			// text박스에 선택된 레코드의 정보 넣기
			// int bbcode, String bbkind, String bbname, int percent, int limit
			int row = ac.jt.getSelectedRow();
			acnum.setText(ac.jt.getValueAt(row, 0).toString());
			cnum.setText(ac.jt.getValueAt(row, 1).toString());
			snum.setText(ac.jt.getValueAt(row, 2).toString());
			bbcode.setText(ac.jt.getValueAt(row, 3).toString());
			day.setText(ac.jt.getValueAt(row, 4).toString());
			money.setText(ac.jt.getValueAt(row, 5).toString());

			// code 관련 활성화/비활성화
			// code text 박스 비활성화
			acnum.setEditable(false);
			// codeCheck버튼 비활성화
			codeCkB.setEnabled(false);
		}
		
		//label 추가하기
		pw.add(lable_acnum);
        pw.add(lable_cnum);
        pw.add(lable_snum);
        pw.add(lable_bbcode);
        pw.add(label_day);
        pw.add(label_money);
        
        codeCkP.add(acnum,"Center");
        codeCkP.add(codeCkB,"East");
        
        //TextField 추가
        pc.add(acnum);
        pc.add(cnum);
        pc.add(snum);
        pc.add(bbcode);
        pc.add(day);
        pc.add(money);

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
			if(insert.accountInsert(this) > 0) {
				messageBox(this,acnum.getText()+" 계좌 등록 성공");
				dispose();
				
				//모든 레코드 가져와서 DefaultTableModel에 올리기
				vo.accountSelect(ac.dt);
				
				if(ac.dt.getRowCount() > 0)
					ac.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
			}
			else {
				messageBox(this,"등록되지 않음");
			}
		}
		else if(btnLabel.equals("수정")) {
			if(update.accountUpdate(this)>0) {
				messageBox(this,"수정 완료");
				dispose();
				
				//모든 레코드 가져와서 DefaultTableModel에 올리기
				vo.accountSelect(ac.dt);
				
				if(ac.dt.getRowCount() > 0)
					ac.jt.setRowSelectionInterval(0, 0);//첫번째 행 선택
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
			if(acnum.getText().trim().equals("")) {
				messageBox(this,"코드 입력하세요");
				acnum.requestFocus(); //포커스 이동
			}
			else {
				if(dao.getCodebycheck(acnum.getText())) {
					messageBox(this,acnum.getText()+"는 사용 가능");
				}
				else {
					messageBox(this,acnum.getText()+"는 중복");
					
					acnum.setText(""); //코드에 들어간 내용 지우고
					acnum.requestFocus();  //커서 여기에 놓기
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}
