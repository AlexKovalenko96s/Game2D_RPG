package ua.kas.main.framework;

import java.awt.Graphics;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected float velX = 0, velY = 0;

	protected ObjectId id;

	public GameObject(float x, float y, ObjectId id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> object);

	public abstract void render(Graphics g);

	public abstract float getX();

	public abstract void setX(float x);

	public abstract float getY();

	public abstract void setY(float y);

	public abstract float getVelX();

	public abstract void setVelX(float velX);

	public abstract float getVelY();

	public abstract void setVelY(float velY);

	public abstract ObjectId getId();
}
