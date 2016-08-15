package ua.kas.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 150, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 350, 100, 50);

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font font0 = new Font("arial", Font.BOLD, 50);
		g.setFont(font0);
		g.setColor(Color.WHITE);
		g.drawString("SPACE GAME", Game.WIDTH / 2, 100);

		Font font1 = new Font("arial", Font.BOLD, 30);
		g.setFont(font1);
		g.drawString("Play", playButton.x + 19, playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
		g2d.draw(helpButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
		g2d.draw(quitButton);
	}
}
