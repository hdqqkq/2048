package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import service.GameService;
import data.Dto;
//Ö÷¿Ø¼þ
public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private Background background;
	private Scoreboard scoreboard;
	private Score scoreNum;
	private MainGame mainGame;
	private Dto dto;
	private GameService gameService;
	public MainPanel(Dto dto, GameService gameService){
		this.dto = dto;
		this.background = new Background(dto);
		this.scoreboard = new Scoreboard();
		this.mainGame = new MainGame(dto, gameService);
		this.gameService = new GameService(dto);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.scoreNum = new Score(dto);
		
		this.background.drawBackground(g);
		
		this.scoreboard.drawText(g);
		
		this.gameService.loadHighScore();
		
		this.scoreNum.drawScore(g);
		
		this.scoreNum.drawHighScore(g);
		
		this.mainGame.GamePaint(g, new GameService(dto).get2Or4());
		
	}
	
}
