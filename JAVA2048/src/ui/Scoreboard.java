package ui;

import java.awt.Graphics;

import data.Resources;
public class Scoreboard {
	protected int s_x = 80;
	protected int s_y = 20;
	protected int h_x = 280;
	protected int h_y = 20;
	//显示两张计分板图片
	public void drawText(Graphics g){
		g.drawImage(Resources.img_score, s_x, s_y, null);		
		g.drawImage(Resources.img_highscore, h_x, h_y, null);
	}
}
