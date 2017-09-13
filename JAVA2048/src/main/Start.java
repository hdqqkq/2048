package main;

import control.GameControl;
import data.Dto;
import service.GameService;
import ui.MainFrame;
import ui.MainPanel;

public class Start {
	public static void main(String arg[]) {
		
		Dto dto = new Dto();	//创建dto对象
		GameService gameService = new GameService(dto);//创建gameservice对象
		MainFrame mainFrame = new MainFrame();		   //创建游戏主窗口
		MainPanel mainPanel = new MainPanel(dto, gameService);//创建游戏panel
		GameControl gameControl = new GameControl(mainPanel, gameService);//创建游戏控制器对象
		mainFrame.setVisible(true);//设置窗口可见
		mainFrame.addKeyListener(gameControl);//添加键盘监听器
		mainFrame.setContentPane(mainPanel);//设置游戏主panel
	}

}
