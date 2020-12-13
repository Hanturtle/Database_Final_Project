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

	JLabel lable_acnum = new JLabel("���¹�ȣ");
	JLabel lable_cnum = new JLabel("���ڵ�");
	JLabel lable_snum = new JLabel("�����ڵ�");
	JLabel lable_bbcode = new JLabel("�����ڵ�");
	JLabel label_day = new JLabel("�ֱ� ������");
	JLabel label_money = new JLabel("�ܾ�");

	JTextField acnum = new JTextField();
	JTextField cnum = new JTextField();
	JTextField snum = new JTextField();
	JTextField bbcode = new JTextField();
	JTextField day = new JTextField();
	JTextField money = new JTextField();

	JButton confirm;
	JButton reset = new JButton("���");

	AccountJTableExam ac;

	JPanel codeCkP = new JPanel(new BorderLayout());
	JButton codeCkB = new JButton("�ڵ� �ߺ� �˻�");

	AccountDAO dao = new AccountDAO();
	AccountInsert insert = new AccountInsert();
	AccountDelete delete = new AccountDelete();
	AccountUpdate update = new AccountUpdate();
	AccountVo vo = new AccountVo();
	
	//������ �Լ�
	public AccountFrame(AccountJTableExam ac, String index) {
		super(ac, "���� ���� ����");
		this.ac = ac;

		// ��ϰ����� ��ư �� ������ ����� ���ϱ�
		if (index.contentEquals("���")) {
			confirm = new JButton(index);
		} 
		else {
			confirm = new JButton("����");

			// text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			// int bbcode, String bbkind, String bbname, int percent, int limit
			int row = ac.jt.getSelectedRow();
			acnum.setText(ac.jt.getValueAt(row, 0).toString());
			cnum.setText(ac.jt.getValueAt(row, 1).toString());
			snum.setText(ac.jt.getValueAt(row, 2).toString());
			bbcode.setText(ac.jt.getValueAt(row, 3).toString());
			day.setText(ac.jt.getValueAt(row, 4).toString());
			money.setText(ac.jt.getValueAt(row, 5).toString());

			// code ���� Ȱ��ȭ/��Ȱ��ȭ
			// code text �ڽ� ��Ȱ��ȭ
			acnum.setEditable(false);
			// codeCheck��ư ��Ȱ��ȭ
			codeCkB.setEnabled(false);
		}
		
		//label �߰��ϱ�
		pw.add(lable_acnum);
        pw.add(lable_cnum);
        pw.add(lable_snum);
        pw.add(lable_bbcode);
        pw.add(label_day);
        pw.add(label_money);
        
        codeCkP.add(acnum,"Center");
        codeCkP.add(codeCkB,"East");
        
        //TextField �߰�
        pc.add(acnum);
        pc.add(cnum);
        pc.add(snum);
        pc.add(bbcode);
        pc.add(day);
        pc.add(money);

        //button �߰�
        ps.add(confirm);
        ps.add(reset);
        
        //panel �߰�
        add(pw,"West");
        add(pc,"Center");
        add(ps,"South");
        
        setSize(300,250);
        setVisible(true);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        //��ư �̺�Ʈ ���
        confirm.addActionListener(this); //���԰� ���� �̺�Ʈ
        reset.addActionListener(this);  //��� �̺�Ʈ
        codeCkB.addActionListener(this);  //code �ߺ� üũ �̺�Ʈ
	}

	
	//��ư �̺�Ʈ ��� ����
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�̺�Ʈ ��ü�� ���� label ��������
		String btnLabel = e.getActionCommand();
		
		if(btnLabel.contentEquals("���")) {
			if(insert.accountInsert(this) > 0) {
				messageBox(this,acnum.getText()+" ���� ��� ����");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.accountSelect(ac.dt);
				
				if(ac.dt.getRowCount() > 0)
					ac.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				messageBox(this,"��ϵ��� ����");
			}
		}
		else if(btnLabel.equals("����")) {
			if(update.accountUpdate(this)>0) {
				messageBox(this,"���� �Ϸ�");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.accountSelect(ac.dt);
				
				if(ac.dt.getRowCount() > 0)
					ac.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				messageBox(this,"�������� ����");
			}
		}
		else if(btnLabel.equals("���")) {
			dispose();
		}
		else if(btnLabel.equals("�ڵ� �ߺ� �˻�")) {
			//code �ڽ��� ���� ������ �޽��� ��� ������ db ����
			if(acnum.getText().trim().equals("")) {
				messageBox(this,"�ڵ� �Է��ϼ���");
				acnum.requestFocus(); //��Ŀ�� �̵�
			}
			else {
				if(dao.getCodebycheck(acnum.getText())) {
					messageBox(this,acnum.getText()+"�� ��� ����");
				}
				else {
					messageBox(this,acnum.getText()+"�� �ߺ�");
					
					acnum.setText(""); //�ڵ忡 �� ���� �����
					acnum.requestFocus();  //Ŀ�� ���⿡ ����
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}
