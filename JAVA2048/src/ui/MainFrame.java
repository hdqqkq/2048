package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	//������
	public MainFrame(){
		this.setTitle("Java2048");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);		//���ô��ڲ�������
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		//���ڼ�����
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(JOptionPane.showConfirmDialog(
						null, 
						"               ȷ���˳���", 
						"��ʾ", 
						JOptionPane.OK_CANCEL_OPTION)== 0 ){
							System.exit(0);
						};		//�˳�ǰ��ʾ
			}
		});
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
