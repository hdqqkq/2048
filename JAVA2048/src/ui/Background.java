package ui;

import java.awt.Graphics;

import data.Dto;
import data.Resources;

public class Background {

	private int x = 50;
	private int y = 120;
	//调用数据传输对象的方法设置次背景的坐标
	public Background(Dto dto) {
		dto.setX(x);
		dto.setY(y);
	}

	public void drawBackground(Graphics g) {
		g.drawImage(Resources.img_bg, 0, 0, null);		//显示背景图片
		g.drawImage(Resources.img_fg, x, y, null);		//显示次背景图片
	}
}
