package ui;

import java.awt.Graphics;

import data.Dto;
import data.Resources;

public class Score extends Scoreboard{
	private static final int SIZE_NUM = 21;
	private static final int SIZE_SCORE = 80;
	private int numPos;
	private Dto dto;
	public Score(Dto dto) {
		this.dto = dto;
	}
	//显示当前得分
	public void drawScore(Graphics g){
		//若当前得分大于最高分，最高分随当前得分更新，且高分更新标记设为真
		if(dto.getScore() > dto.getHighScore())
		{
			dto.setHighScore(dto.getScore());
			dto.setNeedToRefresh(true);
		}
		String scoreString = Integer.toString(dto.getScore());
		for (int i = 0; i < scoreString.length(); i++) {
			int scoreBit = scoreString.charAt(i)-'0';
			this.numPos = scoreBit*SIZE_NUM;
			g.drawImage(Resources.img_num, 
					this.s_x-10+SIZE_SCORE+i*SIZE_NUM-scoreString.length()*10, 
					this.s_y+50, 
					this.s_x-10+SIZE_SCORE+SIZE_NUM+i*SIZE_NUM-scoreString.length()*10, 
					this.s_y+50+SIZE_NUM,
					numPos,
					0,
					SIZE_NUM+numPos,
					SIZE_NUM,
					null);
		}
	}
	//显示最高分
	public void drawHighScore(Graphics g) {
		String highScoreString = Integer.toString(dto.getHighScore());
		for (int i = 0; i < highScoreString.length(); i++) {
			int HighScoreBit = highScoreString.charAt(i)-'0';
			this.numPos = HighScoreBit*SIZE_NUM;
			g.drawImage(Resources.img_num, 
					this.s_x+190+SIZE_SCORE+i*SIZE_NUM-highScoreString.length()*10, 
					this.s_y+50, 
					this.s_x+190+SIZE_SCORE+SIZE_NUM+i*SIZE_NUM-highScoreString.length()*10, 
					this.s_y+50+SIZE_NUM,
					numPos,
					0,
					SIZE_NUM+numPos,
					SIZE_NUM,
					null);
		}
	}
}
