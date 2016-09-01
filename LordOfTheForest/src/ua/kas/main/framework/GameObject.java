package ua.kas.main.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected float velX, velY;

	protected boolean jumping = false;
	protected boolean falling = true;
	protected boolean fire = false;

	protected int facing = 1;
	// 1 = right
	// -1 = left

	protected ObjectId id;

	public GameObject(float x, float y, ObjectId id) {
		super();
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick(LinkedList<GameObject> object);

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

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public int getFacing() {
		return facing;
	}

	public void setFacing(int facing) {
		this.facing = facing;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}
}
