package main;

import control.GameControl;
import data.Dto;
import service.GameService;
import ui.MainFrame;
import ui.MainPanel;

public class Start {
	public static void main(String arg[]) {
		
		Dto dto = new Dto();	//����dto����
		GameService gameService = new GameService(dto);//����gameservice����
		MainFrame mainFrame = new MainFrame();		   //������Ϸ������
		MainPanel mainPanel = new MainPanel(dto, gameService);//������Ϸpanel
		GameControl gameControl = new GameControl(mainPanel, gameService);//������Ϸ����������
		mainFrame.setVisible(true);//���ô��ڿɼ�
		mainFrame.addKeyListener(gameControl);//��Ӽ��̼�����
		mainFrame.setContentPane(mainPanel);//������Ϸ��panel
	}

}
