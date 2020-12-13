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

	JLabel lable_snum = new JLabel("���� �ڵ�");
	JLabel lable_pnum = new JLabel("�ֹι�ȣ");
	JLabel lable_name = new JLabel("�̸�");
	JLabel lable_position = new JLabel("����");
	JLabel label_ph = new JLabel("��ȭ��ȣ");

	JTextField snum = new JTextField();
	JTextField pnum = new JTextField();
	JTextField name = new JTextField();
	JTextField position = new JTextField();
	JTextField ph = new JTextField();

	JButton confirm;
	JButton reset = new JButton("���");

	StaffJTableExam s;

	JPanel codeCkP = new JPanel(new BorderLayout());
	JButton codeCkB = new JButton("�ڵ� �ߺ� �˻�");

	StaffDAO dao = new StaffDAO();
	StaffInsert insert = new StaffInsert();
	StaffDelete delete = new StaffDelete();
	StaffUpdate update = new StaffUpdate();
	StaffVo vo = new StaffVo();
	
	//������ �Լ�
	public StaffFrame(StaffJTableExam s, String index) {
		super(s, "���� ����");
		this.s = s;

		// ��ϰ����� ��ư �� ������ ����� ���ϱ�
		if (index.contentEquals("���")) {
			confirm = new JButton(index);
		} 
		else {
			confirm = new JButton("����");

			// text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row = s.jt.getSelectedRow();
			snum.setText(s.jt.getValueAt(row, 0).toString());
			//pnum.setText(s.jt.getValueAt(row, 1).toString());
			name.setText(s.jt.getValueAt(row, 1).toString());
			position.setText(s.jt.getValueAt(row, 2).toString());
			//ph.setText(s.jt.getValueAt(row, 4).toString());

			// code ���� Ȱ��ȭ/��Ȱ��ȭ
			// code text �ڽ� ��Ȱ��ȭ
			snum.setEditable(false);
			// codeCheck��ư ��Ȱ��ȭ
			codeCkB.setEnabled(false);
		}
		
		//label �߰��ϱ�
		pw.add(lable_snum);
        pw.add(lable_pnum);
        pw.add(lable_name);
        pw.add(lable_position);
        pw.add(label_ph);
        
        codeCkP.add(snum,"Center");
        codeCkP.add(codeCkB,"East");
        
        //TextField �߰�
        pc.add(snum);
        pc.add(pnum);
        pc.add(name);
        pc.add(position);
        pc.add(ph);

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
			if(insert.StaffInsert(this) > 0) {
				messageBox(this,name.getText()+" ���� ��� ����");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.StaffSelect(s.dt);
				
				if(s.dt.getRowCount() > 0)
					s.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				messageBox(this,"��ϵ��� ����");
			}
		}
		else if(btnLabel.equals("����")) {
			if(update.StaffUpdate(this)>0) {
				messageBox(this,"���� �Ϸ�");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.StaffSelect(s.dt);
				
				if(s.dt.getRowCount() > 0)
					s.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
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
			if(snum.getText().trim().equals("")) {
				messageBox(this,"�ڵ� �Է��ϼ���");
				snum.requestFocus(); //��Ŀ�� �̵�
			}
			else {
				if(dao.getCodebycheck(snum.getText())) {
					messageBox(this,snum.getText()+"�� ��� ����");
				}
				else {
					messageBox(this,snum.getText()+"�� �ߺ�");
					
					snum.setText(""); //�ڵ忡 �� ���� �����
					snum.requestFocus();  //Ŀ�� ���⿡ ����
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}
