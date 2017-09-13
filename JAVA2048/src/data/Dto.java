package data;

public class Dto {
	private int score = 0;
	private int highScore = 0;
	private boolean needToRefresh = false;
	private int x;	//�α����ĺ�����
	private int y;	//�α�����������
	private int[][] gameMap = new int[4][4];
	public int getScore() {
		return score;		//������Ϸ����
	}
	public void setScore(int s) {
		score = s;		//������Ϸ����
	}
	public int getHighScore(){
		return highScore;	//������߷�
	}
	public void setHighScore(int hs){
		 highScore = hs;	//������߷�
	}
	public boolean getNeedToReresh(){
		return needToRefresh;
	}
	public void setNeedToRefresh(boolean ntr){
		needToRefresh = ntr;
	}
	public int[][] getGameMap() {
		return gameMap;			//���ط������ֶ�Ӧ����
	}
	public void setGameMap(int[][] gameMap) {
		this.gameMap = gameMap;		//���÷������ֶ�Ӧ����
	}
	//���غ����ô˱�������
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
