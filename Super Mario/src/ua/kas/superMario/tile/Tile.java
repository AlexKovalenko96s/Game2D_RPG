package ua.kas.superMario.tile;

import java.awt.Graphics;

import ua.kas.superMario.Handler;
import ua.kas.superMario.Id;

public class Tile {

	private int x, y;
	private int width, height;
	private int velX, velY;

	private boolean solid;

	private Id id;
	private Handler handler;

	public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
	}

	public void render(Graphics g) {

	}

	public void tick() {

		x += velX;
		y += velY;
	}

	public void die() {

		handler.removeTile(this);
	}

	public Id getId() {
		return id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

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

	public boolean isSolid() {
		return solid;
	}

}
