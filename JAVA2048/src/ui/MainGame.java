package ui;

import java.awt.Graphics;

import service.GameService;
import data.Dto;
import data.Resources;

public class MainGame {

	private static final int SIZE = 100;
	private int x;
	private int y;
	private Dto dto;
	private int[][] gameMap;

	public MainGame(Dto dto, GameService gameService) {
		gameService.newBlock();
		gameService.newBlock();	//游戏开始时，生成2个数字为2或4的方块
		this.dto = dto;
		this.x = dto.getX();
		this.y = dto.getY();
	}

	public void GamePaint(Graphics g, int index) {
		this.gameMap = dto.getGameMap();
		//gameMap数组用于存储数字图片的索引编号
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameMap[i][j] != 0) {
					int blockIndex = gameMap[i][j];
					//不同的索引号显示不同的图片
					switch (blockIndex) {	
					case 1:
						g.drawImage(Resources.imgOf2, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 2:
						g.drawImage(Resources.imgOf4, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 3:
						g.drawImage(Resources.imgOf8, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 4:
						g.drawImage(Resources.imgOf16, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 5:
						g.drawImage(Resources.imgOf32, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 6:
						g.drawImage(Resources.imgOf64, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 7:
						g.drawImage(Resources.imgOf128, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 8:
						g.drawImage(Resources.imgOf256, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 9:
						g.drawImage(Resources.imgOf512, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 10:
						g.drawImage(Resources.imgOf1024, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					case 11:
						g.drawImage(Resources.imgOf2048, this.x + i * SIZE, this.y + j * SIZE, null);
						break;
					default:
						break;
					}
				}
			}
		}
	}
}
