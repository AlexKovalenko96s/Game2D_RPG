package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import ua.kas.main.Animation;
import ua.kas.main.Game;
import ua.kas.main.Handler;
import ua.kas.main.Texture;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Player extends GameObject {

	private float width = 48;
	private float height = 96;
	private float gravity = 0.5f;

	private final float MAX_SPEED = 10;

	private int facing = 1;
	// 1 = right
	// -1 = left

	private Handler handler;
	private Texture texture;
	public Game game;
	private Animation playerWalkRight, playerWalkLeft;

	public Player(float x, float y, ObjectId id, Handler handler, Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
		texture = game.getInstance();
		playerWalkRight = new Animation(10, texture.player[1], texture.player[2], texture.player[3], texture.player[4],
				texture.player[5], texture.player[6]);
		playerWalkLeft = new Animation(10, texture.player[8], texture.player[9], texture.player[10], texture.player[11],
				texture.player[12], texture.player[13]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (velX < 0) {
			facing = -1;
		} else if (velX > 0) {
			facing = 1;
		}

		if (falling || jumping) {
			velY += gravity;
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		Collision(object);
		if (facing == 1) {
			playerWalkRight.runAnimation();
		}
		if (facing == -1) {
			playerWalkLeft.runAnimation();
		}
	}

	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
					falling = true;
				}
				if (getBoundsDown().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 35;
					velX = 0;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - width;
					velX = 0;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (jumping) {
			if (facing == 1) {
				g.drawImage(texture.player_jump[2], (int) x, (int) y, 48, 96, null);
			} else if (facing == -1) {
				g.drawImage(texture.player_jump[3], (int) x, (int) y, 48, 96, null);
			}
		} else {
			if (velX != 0) {
				if (facing == 1) {
					playerWalkRight.drawAnimation(g, (int) x, (int) y, 48, 96);
				} else {
					playerWalkLeft.drawAnimation(g, (int) x, (int) y, 48, 96);
				}
			} else {
				if (facing == 1) {
					g.drawImage(texture.player[0], (int) x, (int) y, 48, 96, null);
				} else if (facing == -1) {
					g.drawImage(texture.player[7], (int) x, (int) y, 48, 96, null);
				}
			}
		}

		/////////// Collision
		// Graphics2D g2d = (Graphics2D) g;
		// g2d.setColor(Color.RED);
		// g2d.draw(getBounds());
		// g2d.draw(getBoundsDown());
		// g2d.draw(getBoundsLeft());
		// g2d.draw(getBoundsRight());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) y, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsDown() {
		return new Rectangle((int) ((int) x + (width / 2) - ((width / 2) / 2)), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int) x + width - 5), (int) y + 5, (int) 5, (int) height - 10);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, (int) 5, (int) height - 10);
	}

}
