package ua.kas.superMario.tile;

import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.superMario.Handler;
import ua.kas.superMario.Id;

public abstract class Tile {

	public int x, y;
	public int width, height;
	public int velX, velY;

	public boolean solid;

	public Id id;
	public Handler handler;

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

	public abstract void render(Graphics g);

	public abstract void tick();

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

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
}
