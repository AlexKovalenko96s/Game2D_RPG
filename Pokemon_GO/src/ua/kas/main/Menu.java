package ua.kas.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	public Rectangle playButton = new Rectangle(Game.WIDTH + 220, Game.HEIGHT + 60, 80, 30);
	public Rectangle helpButton = new Rectangle(Game.WIDTH + 220, Game.HEIGHT + 100, 80, 30);
	public Rectangle exitButton = new Rectangle(Game.WIDTH + 220, Game.HEIGHT + 140, 80, 30);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font font1 = new Font("arial", Font.BOLD, 20);
		g.setColor(Color.WHITE);
		g.setFont(font1);
		g.drawString("Play", playButton.x + 22, playButton.y + 22);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 22, helpButton.y + 22);
		g2d.draw(helpButton);
		g.drawString("Exit", exitButton.x + 22, exitButton.y + 22);
		g2d.draw(exitButton);
	}
}
