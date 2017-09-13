package ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	//主界面
	public MainFrame(){
		this.setTitle("Java2048");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);		//设置窗口不可缩放
		this.setSize(500,600);
		this.setLocationRelativeTo(null);
		//窗口监听器
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(JOptionPane.showConfirmDialog(
						null, 
						"               确定退出？", 
						"提示", 
						JOptionPane.OK_CANCEL_OPTION)== 0 ){
							System.exit(0);
						};		//退出前提示
			}
		});
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
