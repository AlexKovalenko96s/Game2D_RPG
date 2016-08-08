package ua.kas.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	private double x;
	private double y;

	private BufferedImage player = null;

	public Player(double x, double y, Game game) {
		this.x = x;
		this.y = y;
		player = game.getPlayer_img();
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(player, (int) x, (int) y, null);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
