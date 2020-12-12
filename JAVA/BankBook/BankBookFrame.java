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

	JLabel lable_bbcode = new JLabel("�ڵ�");
	JLabel lable_bbkind = new JLabel("����");
	JLabel lable_bbname = new JLabel("�̸�");
	JLabel lable_bbpercent = new JLabel("����");
	JLabel label_bblimit = new JLabel("�ѵ�");

	JTextField code = new JTextField();
	JTextField kind = new JTextField();
	JTextField name = new JTextField();
	JTextField percent = new JTextField();
	JTextField limit = new JTextField();

	JButton confirm;
	JButton reset = new JButton("���");

	BankBookJTableExam bb;

	JPanel codeCkP = new JPanel(new BorderLayout());
	JButton codeCkB = new JButton("�ڵ� �ߺ� �˻�");

	BankBookDAO dao = new BankBookDAO();
	BankBookInsert insert = new BankBookInsert();
	BankBookDelete delete = new BankBookDelete();
	BankBookUpdate update = new BankBookUpdate();
	BankBookVo vo = new BankBookVo();
	
	//������ �Լ�
	public BankBookFrame(BankBookJTableExam bb, String index) {
		super(bb, "���� ���� ����");
		this.bb = bb;

		// ��ϰ����� ��ư �� ������ ����� ���ϱ�
		if (index.contentEquals("���")) {
			confirm = new JButton(index);
		} 
		else {
			confirm = new JButton("����");

			// text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			// int bbcode, String bbkind, String bbname, int percent, int limit
			int row = bb.jt.getSelectedRow();
			code.setText(bb.jt.getValueAt(row, 0).toString());
			kind.setText(bb.jt.getValueAt(row, 1).toString());
			name.setText(bb.jt.getValueAt(row, 2).toString());
			percent.setText(bb.jt.getValueAt(row, 3).toString());
			limit.setText(bb.jt.getValueAt(row, 4).toString());

			// code ���� Ȱ��ȭ/��Ȱ��ȭ
			// code text �ڽ� ��Ȱ��ȭ
			code.setEditable(false);
			// codeCheck��ư ��Ȱ��ȭ
			codeCkB.setEnabled(false);
		}
		
		//label �߰��ϱ�
		pw.add(lable_bbcode);
        pw.add(lable_bbkind);
        pw.add(lable_bbname);
        pw.add(lable_bbpercent);
        pw.add(label_bblimit);
        
        codeCkP.add(code,"Center");
        codeCkP.add(codeCkB,"East");
        
        //TextField �߰�
        pc.add(code);
        pc.add(kind);
        pc.add(name);
        pc.add(percent);
        pc.add(limit);

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
			if(insert.bankbookInsert(this) > 0) {
				messageBox(this,name.getText()+" ���� ��� ����");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.bankbookSelect(bb.dt);
				
				if(bb.dt.getRowCount() > 0)
					bb.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				messageBox(this,"��ϵ��� ����");
			}
		}
		else if(btnLabel.equals("����")) {
			if(update.bankbookUpdate(this)>0) {
				messageBox(this,"���� �Ϸ�");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.bankbookSelect(bb.dt);
				
				if(bb.dt.getRowCount() > 0)
					bb.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
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
			if(code.getText().trim().equals("")) {
				messageBox(this,"�ڵ� �Է��ϼ���");
				code.requestFocus(); //��Ŀ�� �̵�
			}
			else {
				if(dao.getCodebycheck(code.getText())) {
					messageBox(this,code.getText()+"�� ��� ����");
				}
				else {
					messageBox(this,code.getText()+"�� �ߺ�");
					
					code.setText(""); //�ڵ忡 �� ���� �����
					code.requestFocus();  //Ŀ�� ���⿡ ����
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}
