package ua.kas.superMario.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.superMario.Handler;
import ua.kas.superMario.Id;

public abstract class Entity {

	public int x, y;
	public int width, height;
	public int velX, velY;

	public boolean solid;

	public Id id;
	public Handler handler;

	public Entity(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
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
		handler.removeEntity(this);
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
		return new Rectangle(getX(), getY(), width, height);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle(getX() + 1, getY(), width - 2, 5);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle(getX() + 1, getY() + height - 5, width - 2, 5);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle(getX(), getY() + 1, 5, height - 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle(getX() + width - 5, getY() + 1, 5, height - 2);
	}
}