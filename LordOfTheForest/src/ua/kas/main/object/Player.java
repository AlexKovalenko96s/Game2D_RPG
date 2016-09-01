package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import ua.kas.main.Animation;
import ua.kas.main.Game;
import ua.kas.main.Texture;
import ua.kas.main.framework.GameObject;
import ua.kas.main.framework.ObjectId;

public class Player extends GameObject {

	private final int WIDTH = 32;
	private final int HEIGHT = 64;
	private final float GRAVITY = 0.5f;
	private final float MAX_SPEED = 10;

	public Game game;

	private Texture texture;
	private Animation animationWalkRight, animationWalkLeft;

	public Player(float x, float y, ObjectId id, Game game, Texture texture) {
		super(x, y, id);
		this.game = game;
		this.texture = texture;
		animationWalkRight = new Animation(10, texture.player[1], texture.player[2], texture.player[3],
				texture.player[4], texture.player[5]);
		animationWalkLeft = new Animation(10, texture.player[7], texture.player[8], texture.player[9],
				texture.player[10], texture.player[11]);
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (velX > 0) {
			facing = 1;
		} else if (velX < 0) {
			facing = -1;
		}

		if (falling || jumping) {
			velY += GRAVITY;
			if (velY > MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}

		collision(object);

		if (facing == 1) {
			animationWalkRight.runAnimation();
		} else if (facing == -1) {
			animationWalkLeft.runAnimation();
		}
	}

	@Override
	public void render(Graphics g) {
		if (jumping) {
			if (facing == 1) {
				g.drawImage(texture.playerJump[0], (int) x, (int) y, null);
			} else if (facing == -1) {
				g.drawImage(texture.playerJump[1], (int) x, (int) y, null);
			}
		} else {
			if (velX != 0) {
				if (facing == 1 && !fire) {
					animationWalkRight.drawAnimation(g, (int) x, (int) y);
				} else if (facing == -1 && !fire) {
					animationWalkLeft.drawAnimation(g, (int) x, (int) y);
				}

			} else {
				if (facing == 1 && !fire) {
					g.drawImage(texture.player[0], (int) x, (int) y, null);
				} else if (facing == -1 && !fire) {
					g.drawImage(texture.player[6], (int) x, (int) y, null);
				} else if (facing == 1 && fire) {
					g.drawImage(texture.playerHit[0], (int) x, (int) y, null);
				} else if (facing == -1 && fire) {
					g.drawImage(texture.playerHit[1], (int) x - 16, (int) y, null);
				}
			}
		}

		// Graphics2D g2d = (Graphics2D) g;
		// g2d.setColor(Color.RED);
		// g2d.draw(getBounds());
		// g2d.draw(getBoundsDown());
		// g2d.draw(getBoundsLeft());
		// g2d.draw(getBoundsRight());
	}

	private void collision(LinkedList<GameObject> object) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			if (tempObject.getId() == ObjectId.Block) {
				if (getBoundsDown().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - HEIGHT;
					velY = 0;
					jumping = false;
					falling = false;
				} else {
					falling = true;
				}
				if (getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() + 32;
					velY = 0;
					jumping = false;
					falling = true;
				}
				if (getBoundsLeft().intersects(tempObject.getBounds())) {
					x = tempObject.getX() + 32;
					velX = 0;
				}
				if (getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX() - WIDTH;
					velX = 0;
				}
			}
		}
	}

	@Override // top
	public Rectangle getBounds() {
		return new Rectangle((int) x + 5, (int) y, WIDTH - 10, HEIGHT / 2);
	}

	public Rectangle getBoundsDown() {
		return new Rectangle((int) x + 5, (int) y + HEIGHT / 2, WIDTH - 10, HEIGHT / 2);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 5, 5, HEIGHT - 10);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + WIDTH - 5, (int) y + 5, 5, HEIGHT - 10);
	}

	public Rectangle getBoundsFireRight() {
		return new Rectangle((int) x + 32, (int) y, 16, HEIGHT);
	}

	public Rectangle getBoundsFireLeft() {
		return new Rectangle((int) x - 16, (int) y, 16, HEIGHT);
	}
}
