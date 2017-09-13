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
	//构造函数，通过传递dto的参数来初始化GamedService类
	public GameService(Dto dto) {
		this.random = new Random();
		this.dto = dto;
		this.Score = dto.getScore();
		this.gameMap = dto.getGameMap();
	}
	public void loadHighScore()		//从文件载入最高分
	{
		BufferedReader br;
		try{
				br=new BufferedReader(new FileReader(Resources.record));
				dto.setHighScore(Integer.valueOf(br.readLine()).intValue());
				br.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,"找不到同目录下的Record文件或文件已损坏!", "提示", JOptionPane.INFORMATION_MESSAGE);
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
				JOptionPane.showMessageDialog(null,"找不到同目录下的Record文件或文件已损坏!", "提示", JOptionPane.INFORMATION_MESSAGE);
			};
		}
	}
	public void restart()	//重新开始，地图、分数清零，生成2个方块并入栈
	{
		dto.setScore(0);
		dto.setNeedToRefresh(false);
		this.dto.setGameMap(new int[4][4]);
		gameMap = new int[4][4];
		Score = 0;
		newBlock();
		newBlock();
	}
	// 获取新坐标
	public int getBlockPos() {
		int pos = random.nextInt(4);//得到一个0到3的随机数，用于确定新生成方块的位置
		return pos;
	}

	// 获取新方块号
	public int get2Or4() {
		int x = random.nextInt(8); //得到一个1到2的随机数，用于确定新方块中是2或4,得到4的概率约为1/8
		if(x != 0)
			return 1;
		else
			return 2;
	}

	// 新建一个方块
	public void newBlock() {
		int x = getBlockPos();
		int y = getBlockPos();
		//若方块已被占用，随机另外一个坐标
		while (gameMap[x][y] != 0) {
			x = getBlockPos();
			y = getBlockPos();
		}
		//获取一个方块编号
		gameMap[x][y] = get2Or4();
		this.dto.setGameMap(gameMap);
	}

	// 判断地图是不是满
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
	//判断是否可以移动
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
	//判断游戏是否结束
	public void isGameOver() {
		if (isFull()) {
			Resources.playSound("res/fail.wav");
			Object[]  options = {"重新开始","退出游戏"};
			int choice = JOptionPane.showOptionDialog(
					null, 
					"\no(╯□╰)o 游戏结束!\n      分数为："+this.dto.getScore()+"\n", 
					"提示",  
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, options,
					options[0]
					);
			refreshHighscore();
			//选择重新开始还是结束游戏
			if(choice ==0){
				restart();
			}
			else {
				System.exit(0);
			}
		} 
		else if(is2048()) {
			Resources.playSound("res/victory.wav");
			Object[]  options = {"重新开始","退出游戏"};
			int choice = JOptionPane.showOptionDialog(
					null, 
					"   Y^o^Y恭喜你！达成2048 ", 
					"提示",  
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null, options,
					options[0]
					);
			refreshHighscore();
			//选择重新开始还是结束游戏
			if(choice ==0){
				restart();
			}
			else {
				System.exit(0);
			}
		}
	}

	// 判断是否合成2048
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

	// 左移
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

	// 右移
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

	// 下移
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

	// 上移
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

	// 左消
	public int removeLeft() {
		int isRemove = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 3; i++) {
				while (gameMap[i][j] == gameMap[i + 1][j]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j]++;
					gameMap[i + 1][j] = 0;
					// 加分
					this.Score += Math.pow(2, gameMap[i][j]);	//分数的增量为合并后产生的数字大小
					this.dto.setScore(Score);
					isRemove = 1;
				}
			}
		}
		moveLeft();
		return isRemove;
	}

	// 右消
	public int removeRight() {
		int isRemove = 0;
		for (int j = 0; j < 4; j++) {
			for (int i = 3; i > 0; i--) {
				while (gameMap[i][j] == gameMap[i - 1][j]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j] = gameMap[i - 1][j] + 1;
					gameMap[i - 1][j] = 0;
					// 加分
					this.Score += Math.pow(2, gameMap[i][j]);
					this.dto.setScore(Score);
					isRemove = 1;
				}
			}
		}
		moveRight();
		return isRemove;
	}

	// 上消
	public int removeUp() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				while (gameMap[i][j] == gameMap[i][j + 1]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j] = gameMap[i][j + 1] + 1;
					gameMap[i][j + 1] = 0;
					// 加分
					this.Score += Math.pow(2, gameMap[i][j]);
					this.dto.setScore(Score);
					isRemove = 1;
				}
			}
		}
		moveUp();
		return isRemove;
	}

	// 下消
	public int removeDown() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j > 0; j--) {
				while (gameMap[i][j] == gameMap[i][j - 1]
						&& gameMap[i][j] != 11 && gameMap[i][j] != 0) {
					gameMap[i][j] = gameMap[i][j - 1] + 1;
					gameMap[i][j - 1] = 0;
					// 加分
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
