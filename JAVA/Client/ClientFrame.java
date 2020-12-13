package Client;

import Client.*;
import BankConsulting.FrameMain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ClientFrame extends JDialog implements ActionListener {

	JPanel pw = new JPanel(new GridLayout(4, 1)); //label
	JPanel pc = new JPanel(new GridLayout(4, 1));  //textfield
	JPanel ps = new JPanel();  //button

	JLabel lable_cnum = new JLabel("�� �ڵ�");
	JLabel lable_pnum = new JLabel("�ֹι�ȣ");
	JLabel lable_name = new JLabel("�̸�");
	JLabel label_ph = new JLabel("��ȭ��ȣ");

	JTextField cnum = new JTextField();
	JTextField pnum = new JTextField();
	JTextField name = new JTextField();
	JTextField ph = new JTextField();

	JButton confirm;
	JButton reset = new JButton("���");

	ClientJTableExam c;

	JPanel codeCkP = new JPanel(new BorderLayout());
	JButton codeCkB = new JButton("�ڵ� �ߺ� �˻�");

	ClientDAO dao = new ClientDAO();
	ClientInsert insert = new ClientInsert();
	ClientDelete delete = new ClientDelete();
	ClientUpdate update = new ClientUpdate();
	ClientVo vo = new ClientVo();
	
	//������ �Լ�
	public ClientFrame(ClientJTableExam c, String index) {
		super(c, "���� ����");
		this.c = c;

		// ��ϰ����� ��ư �� ������ ����� ���ϱ�
		if (index.contentEquals("���")) {
			confirm = new JButton(index);
		} 
		else {
			confirm = new JButton("����");

			// text�ڽ��� ���õ� ���ڵ��� ���� �ֱ�
			int row = c.jt.getSelectedRow();
			cnum.setText(c.jt.getValueAt(row, 0).toString());
			pnum.setText(c.jt.getValueAt(row, 2).toString());
			name.setText(c.jt.getValueAt(row, 1).toString());

			// code ���� Ȱ��ȭ/��Ȱ��ȭ
			// code text �ڽ� ��Ȱ��ȭ
			cnum.setEditable(false);
			// codeCheck��ư ��Ȱ��ȭ
			codeCkB.setEnabled(false);
		}
		
		//label �߰��ϱ�
		pw.add(lable_cnum);
        pw.add(lable_pnum);
        pw.add(lable_name);
        pw.add(label_ph);
        
        codeCkP.add(cnum,"Center");
        codeCkP.add(codeCkB,"East");
        
        //TextField �߰�
        pc.add(cnum);
        pc.add(pnum);
        pc.add(name);
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
			if(insert.clientInsert(this) > 0) {
				messageBox(this,name.getText()+" �� ��� ����");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.clientSelect(c.dt);
				
				if(c.dt.getRowCount() > 0)
					c.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
			}
			else {
				messageBox(this,"��ϵ��� ����");
			}
		}
		else if(btnLabel.equals("����")) {
			if(update.clientUpdate(this)>0) {
				messageBox(this,"���� �Ϸ�");
				dispose();
				
				//��� ���ڵ� �����ͼ� DefaultTableModel�� �ø���
				vo.clientSelect(c.dt);
				
				if(c.dt.getRowCount() > 0)
					c.jt.setRowSelectionInterval(0, 0);//ù��° �� ����
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
			if(cnum.getText().trim().equals("")) {
				messageBox(this,"�ڵ� �Է��ϼ���");
				cnum.requestFocus(); //��Ŀ�� �̵�
			}
			else {
				if(dao.getCodebycheck(cnum.getText())) {
					messageBox(this,cnum.getText()+"�� ��� ����");
				}
				else {
					messageBox(this,cnum.getText()+"�� �ߺ�");
					
					cnum.setText(""); //�ڵ忡 �� ���� �����
					cnum.requestFocus();  //Ŀ�� ���⿡ ����
				}
			}
		}
	}
	
	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog( (Component)obj , message);
	}

}
