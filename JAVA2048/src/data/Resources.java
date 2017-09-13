package data;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.swing.ImageIcon;

//导入资源文件
public class Resources {
	public static Image img_num = new ImageIcon("res/num.png").getImage();
	public static Image img_fg = new ImageIcon("res/fg.png").getImage();
	public static Image img_bg = new ImageIcon("res/bg.png").getImage();
	public static Image img_score = new ImageIcon("res/score.png").getImage();
	public static Image img_highscore = new ImageIcon("res/highscore.png").getImage();
	public static Image imgOf2 = new ImageIcon("res/2.png").getImage();
	public static Image imgOf4 = new ImageIcon("res/4.png").getImage();
	public static Image imgOf8 = new ImageIcon("res/8.png").getImage();
	public static Image imgOf16 = new ImageIcon("res/16.png").getImage();
	public static Image imgOf32 = new ImageIcon("res/32.png").getImage();
	public static Image imgOf64 = new ImageIcon("res/64.png").getImage();
	public static Image imgOf128 = new ImageIcon("res/128.png").getImage();
	public static Image imgOf256 = new ImageIcon("res/256.png").getImage();
	public static Image imgOf512 = new ImageIcon("res/512.png").getImage();
	public static Image imgOf1024 = new ImageIcon("res/1024.png").getImage();
	public static Image imgOf2048 = new ImageIcon("res/2048.png").getImage();
	public static File record = new File("res/record.txt");
	//播放游戏音效的静态方法，参数是声音文件名
	public static void playSound(String soundName)
	{
		try {
				FileInputStream in = new FileInputStream(soundName);
				AudioStream as = new AudioStream(in);
				AudioPlayer.player.start(as);
		} catch (Exception e) {}
	}
}
