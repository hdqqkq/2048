package service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

import javax.swing.JOptionPane;

import data.Dto;
import data.Resources;

public class GameService{

	private int Score;
	private Random random;
	private Dto dto;
	private int[][] gameMap;
	//���캯����ͨ������dto�Ĳ�������ʼ��GamedService��
	public GameService(Dto dto) {
		this.random = new Random();
		this.dto = dto;
		this.Score = dto.getScore();
		this.gameMap = dto.getGameMap();
	}
	public void loadHighScore()		//���ļ�������߷�
	{
		BufferedReader br;
		try{
				br=new BufferedReader(new FileReader(Resources.record));
				dto.setHighScore(Integer.valueOf(br.readLine()).intValue());
				br.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"�Ҳ���ͬĿ¼�µ�Record�ļ����ļ�����!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}
	public void refreshHighscore()
	{
		if(dto.getNeedToReresh()){
			BufferedWriter bw;
			try{
				bw=new BufferedWriter(new FileWriter(Resources.record));
				bw.write(String.valueOf(dto.getHighScore()),0,String.valueOf(dto.getHighScore()).length());
				bw.close();
				}catch(Exception e1){
				JOptionPane.showMessageDialog(null,"�Ҳ���ͬĿ¼�µ�Record�ļ����ļ�����!", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			};
		}
	}
	public void restart()	//���¿�ʼ����ͼ���������㣬����2�����鲢��ջ
	{
		dto.setScore(0);
		dto.setNeedToRefresh(false);
		this.dto.setGameMap(new int[4][4]);
		gameMap = new int[4][4];
		Score = 0;
		newBlock();
		newBlock();
	}
	// ��ȡ������
	public int getBlockPos() {
		int pos = random.nextInt(4);//�õ�һ��0��3�������������ȷ�������ɷ����λ��
		return pos;
	}

	// ��ȡ�·����
	public int get2Or4() {
		int x = random.nextInt(8); //�õ�һ��1��2�������������ȷ���·�������2��4,�õ�4�ĸ���ԼΪ1/8
		if(x != 0)
			return 1;
		else
			return 2;
	}

	// �½�һ������
	public void newBlock() {
		int x = getBlockPos();
		int y = getBlockPos();
		//�������ѱ�ռ�ã��������һ������
		while (gameMap[x][y] != 0) {
			x = getBlockPos();
			y = getBlockPos();
		}
		//��ȡһ��������
		gameMap[x][y] = get2Or4();
		this.dto.setGameMap(gameMap);
	}

	// �жϵ�ͼ�ǲ�����
	public boolean isFull() {
		int blockCount = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameMap[i][j] != 0) {
					blockCount++;
				}
			}
		}
		if (blockCount == 16 && !canRemove()) {
			return true;
		} 
		else {
			return false;
		}
	}
	//�ж��Ƿ�����ƶ�
	public boolean canRemove() {
		boolean canRemove = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(i + 1 < 4)
					if(gameMap[i][j] == gameMap[i + 1][j]){
						canRemove = true;
						break;
					}
				if(i + 1 < 4)
					if(gameMap[i][j] == gameMap[i + 1][j]){
						canRemove = true;
						break;
					}
				if(i - 1 > -1)
					if(gameMap[i][j] == gameMap[i - 1][j]){
						canRemove = true;
						break;
					}
				if(j - 1 > -1)
					if(gameMap[i][j] == gameMap[i][j - 1]){
						canRemove = true;
						break;
				}
			}
		}
		return canRemove;
	}
	//�ж���Ϸ�Ƿ����
	public void isGameOver() {
		if (isFull()) {
			Resources.playSound("res/fail.wav");
			Object[]  options = {"���¿�ʼ","�˳���Ϸ"};
			int choice = JOptionPane.showOptionDialog(
					null, 
					"\no(�s���t)o ��Ϸ����!\n      ����Ϊ��"+this.dto.getScore()+"\n", 
					"��ʾ",  
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, options,
					options[0]
					);
			refreshHighscore();
			//ѡ�����¿�ʼ���ǽ�����Ϸ
			if(choice ==0){
				restart();
			}
			else {
				System.exit(0);
			}
		} 
		else if(is2048()) {
			Resources.playSound("res/victory.wav");
			Object[]  options = {"���¿�ʼ","�˳���Ϸ"};
			int choice = JOptionPane.showOptionDialog(
					null, 
					"   Y^o^Y��ϲ�㣡���2048 ", 
					"��ʾ",  
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, options,
					options[0]
					);
			refreshHighscore();
			//ѡ�����¿�ʼ���ǽ�����Ϸ
			if(choice ==0){
				restart();
			}
			else {
				System.exit(0);
			}
		}
	}

	// �ж��Ƿ�ϳ�2048
	public boolean is2048() {
		boolean isok = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameMap[i][j] == 11) {
					isok = true;
				}
			}
		}
		return isok;
	}

	// ����
	public int moveLeft() {
		int isMove = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 1; i < 4; i++) {
				int mov_i = i;
				int mov_j = j;
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i - 1][mov_j] == 0) {
					gameMap[mov_i - 1][mov_j] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_i > 1) {
						mov_i--;
					}
					isMove = 1;
				}
			}
		}
		return isMove;
	}

	// ����
	public int moveRight() {
		int isMove = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 2; i >= 0; i--) {
				int mov_i = i;
				int mov_j = j;
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i + 1][mov_j] == 0) {
					gameMap[mov_i + 1][mov_j] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_i < 2) {
						mov_i++;
					}
					isMove = 1;
				}
			}
		}
		return isMove;
	}

	// ����
	public int moveDown() {
		int isMove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j >= 0; j--) {
				int mov_i = i;
				int mov_j = j;
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i][mov_j + 1] == 0) {
					gameMap[mov_i][mov_j + 1] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_j < 2) {
						mov_j++;
					}
					isMove = 1;
				}
			}
		}
		return isMove;
	}

	// ����
	public int moveUp() {
		int isMove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 1; j < 4; j++) {
				int mov_i = i;
				int mov_j = j;
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i][mov_j - 1] == 0) {
					gameMap[mov_i][mov_j - 1] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_j > 1) {
						mov_j--;
					}
					isMove = 1;
				}
			}
		}
		return isMove;
	}

	// ����
	public int removeLeft() {
		int isRemove = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {
				while (gameMap[i][j] == gameMap[i + 1][j]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j]++;
					gameMap[i + 1][j] = 0;
					// �ӷ�
					this.Score += Math.pow(2, gameMap[i][j]);	//����������Ϊ�ϲ�����������ִ�С
					this.dto.setScore(Score);
					isRemove = 1;
				}
			}
		}
		moveLeft();
		return isRemove;
	}

	// ����
	public int removeRight() {
		int isRemove = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i > 0; i--) {
				while (gameMap[i][j] == gameMap[i - 1][j]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j] = gameMap[i - 1][j] + 1;
					gameMap[i - 1][j] = 0;
					// �ӷ�
					this.Score += Math.pow(2, gameMap[i][j]);
					this.dto.setScore(Score);
					isRemove = 1;
				}
			}
		}
		moveRight();
		return isRemove;
	}

	// ����
	public int removeUp() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				while (gameMap[i][j] == gameMap[i][j + 1]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j] = gameMap[i][j + 1] + 1;
					gameMap[i][j + 1] = 0;
					// �ӷ�
					this.Score += Math.pow(2, gameMap[i][j]);
					this.dto.setScore(Score);
					isRemove = 1;
				}
			}
		}
		moveUp();
		return isRemove;
	}

	// ����
	public int removeDown() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				while (gameMap[i][j] == gameMap[i][j - 1]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j] = gameMap[i][j - 1] + 1;
					gameMap[i][j - 1] = 0;
					// �ӷ�
					this.Score += Math.pow(2, gameMap[i][j]);
					this.dto.setScore(Score);
					isRemove = 1;
				}
			}
		}
		moveDown();
		return isRemove;
	}
}
