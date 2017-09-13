package ui;

import java.awt.Graphics;

import data.Dto;
import data.Resources;

public class Background {

	private int x = 50;
	private int y = 120;
	//�������ݴ������ķ������ôα���������
	public Background(Dto dto) {
		dto.setX(x);
		dto.setY(y);
	}

	public void drawBackground(Graphics g) {
		g.drawImage(Resources.img_bg, 0, 0, null);		//��ʾ����ͼƬ
		g.drawImage(Resources.img_fg, x, y, null);		//��ʾ�α���ͼƬ
	}
}
