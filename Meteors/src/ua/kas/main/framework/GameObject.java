package ua.kas.main.framework;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x;
	protected float y;

	protected float velX;
	protected float velY;

	protected int width;
	protected int height;

	protected ObjectId objectId;

	public GameObject(float x, float y, ObjectId objectId) {
		this.x = x;
		this.y = y;
		this.objectId = objectId;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}
}
