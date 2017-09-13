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
		// ����
		case 37: {
			int isMove = gameService.moveLeft();
			int isRemove = gameService.removeLeft();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();	
				Resources.playSound("res/slide.wav");  //���Ż�������
			}
			else{
				Resources.playSound("res/immovable.wav");  //���Ų����ƶ�����
			}
		}
			break;
		// ����
		case 38: {
			int isMove = gameService.moveUp();
			int isRemove = gameService.removeUp();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();
				Resources.playSound("res/slide.wav");  //���Ż�������
			}
			else{
				Resources.playSound("res/immovable.wav");  //���Ų����ƶ�����
			}
		}
			break;
		// ����
		case 39: {
			int isMove = gameService.moveRight();
			int isRemove = gameService.removeRight();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();
				Resources.playSound("res/slide.wav");  //���Ż�������
			}
			else{
				Resources.playSound("res/immovable.wav");  //���Ų����ƶ�����
			}
		}
			break;
		// ����
		case 40: {
			int isMove = gameService.moveDown();
			int isRemove = gameService.removeDown();
			if (isMove==1||isRemove==1) {
				gameService.newBlock();
				Resources.playSound("res/slide.wav");  //���Ż�������
			}
			else{
				Resources.playSound("res/immovable.wav");  //���Ų����ƶ�����
			}
		}
			break;
		case 27: {
			if(JOptionPane.showConfirmDialog(
					null, 
					"               �˳���Ϸ��", 
					"��ʾ", 
					JOptionPane.OK_CANCEL_OPTION)== 0) {
						System.exit(0);
					};
		}
			break;
		case 112: {
			if(JOptionPane.showConfirmDialog(
					null, 
					"               ���¿�ʼ��", 
					"��ʾ", 
					JOptionPane.OK_CANCEL_OPTION)== 0) {
						gameService.restart();
					};	//���¿�ʼǰ��ʾ
		}
			break;
		default:
			break;
		}
		this.mainPanel.repaint();	//ˢ����Ϸ����
		gameService.isGameOver();	//�ж��ǲ���gameover
		this.mainPanel.repaint();
	}
}
