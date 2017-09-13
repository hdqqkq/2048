package data;

public class Dto {
	private int score = 0;
	private int highScore = 0;
	private boolean needToRefresh = false;
	private int x;	//次背景的横坐标
	private int y;	//次背景的纵坐标
	private int[][] gameMap = new int[4][4];
	public int getScore() {
		return score;		//返回游戏分数
	}
	public void setScore(int s) {
		score = s;		//设置游戏分数
	}
	public int getHighScore(){
		return highScore;	//返回最高分
	}
	public void setHighScore(int hs){
		 highScore = hs;	//设置最高分
	}
	public boolean getNeedToReresh(){
		return needToRefresh;
	}
	public void setNeedToRefresh(boolean ntr){
		needToRefresh = ntr;
	}
	public int[][] getGameMap() {
		return gameMap;			//返回方块数字对应号码
	}
	public void setGameMap(int[][] gameMap) {
		this.gameMap = gameMap;		//设置方块数字对应索引
	}
	//返回和设置此背景坐标
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
