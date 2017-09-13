package control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

import data.Resources;
import service.GameService;
import ui.MainPanel;

public class GameControl extends KeyAdapter {

	private MainPanel mainPanel;
	private GameService gameService;
	public GameControl(MainPanel mainPanel, GameService gameService) {
		this.mainPanel = mainPanel;
		this.gameService = gameService;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		switch (e.getKeyCode()) {
		// 左移
		case 37: {
			int isMove = gameService.moveLeft();
			int isRemove = gameService.removeLeft();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();	
				Resources.playSound("res/slide.wav");  //播放滑动声音
			}
			else{
				Resources.playSound("res/immovable.wav");  //播放不可移动声音
			}
		}
			break;
		// 上移
		case 38: {
			int isMove = gameService.moveUp();
			int isRemove = gameService.removeUp();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();
				Resources.playSound("res/slide.wav");  //播放滑动声音
			}
			else{
				Resources.playSound("res/immovable.wav");  //播放不可移动声音
			}
		}
			break;
		// 右移
		case 39: {
			int isMove = gameService.moveRight();
			int isRemove = gameService.removeRight();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();
				Resources.playSound("res/slide.wav");  //播放滑动声音
			}
			else{
				Resources.playSound("res/immovable.wav");  //播放不可移动声音
			}
		}
			break;
		// 下移
		case 40: {
			int isMove = gameService.moveDown();
			int isRemove = gameService.removeDown();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();
				Resources.playSound("res/slide.wav");  //播放滑动声音
			}
			else{
				Resources.playSound("res/immovable.wav");  //播放不可移动声音
			}
		}
			break;
		case 27: {
			if(JOptionPane.showConfirmDialog(
					null, 
					"               退出游戏？", 
					"提示", 
					JOptionPane.OK_CANCEL_OPTION)== 0) {
						System.exit(0);
					};
		}
			break;
		case 112: {
			if(JOptionPane.showConfirmDialog(
					null, 
					"               重新开始？", 
					"提示", 
					JOptionPane.OK_CANCEL_OPTION)== 0) {
						gameService.restart();
					};	//重新开始前提示
		}
			break;
		default:
			break;
		}
		this.mainPanel.repaint();	//刷新游戏窗口
		gameService.isGameOver();	//判断是不是gameover
		this.mainPanel.repaint();
	}
}
