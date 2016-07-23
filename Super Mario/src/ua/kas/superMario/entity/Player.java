package ua.kas.superMario.entity;

import java.awt.Graphics;

import ua.kas.superMario.Handler;
import ua.kas.superMario.Id;
import ua.kas.superMario.Main;
import ua.kas.superMario.tile.Tile;

public class Player extends Entity {

	private int frame = 0;
	private int frameDelay = 0;

	private boolean animate = false;

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	@Override
	public void render(Graphics g) {

		if (facing == 0 && animate) {
			g.drawImage(Main.player[frame + 4].getBufferedImage(), x, y, width, height, null);
		}

		if (facing == 1 && animate) {
			g.drawImage(Main.player[frame].getBufferedImage(), x, y, width, height, null);
		}

		else if (facing == 1 && !animate) {
			g.drawImage(Main.player[0].getBufferedImage(), x, y, width, height, null);
		}

		else if (facing == 0 && !animate) {
			g.drawImage(Main.player[4].getBufferedImage(), x, y, width, height, null);
		}

		// g.setColor(Color.BLUE); simple rectangle
		// g.fillRect(x, y, width, height);
	}

	@Override
	public void tick() {

		x += velX;
		y += velY;

		if (x <= 0) {
			x = 0;
		}
		//
		// if (y <= 0) {
		// y = 0;
		// }

		if (x + width >= 1090) {
			x = 1090 - width;
		}

		if (y + height >= 771) {
			y = 771 - height;
		}

		if (velX != 0) {
			animate = true;
		} else {
			animate = false;
		}

		for (Tile t : handler.tile) {
			if (!t.solid) {
				break;
			}

			if (t.getId() == Id.Wall) {
				if (getBoundsTop().intersects(t.getBounds())) {
					setVelY(0);
					if (jumping) {
						jumping = false;
						gravity = 0.0;
						falling = true;
					}
				}

				if (getBoundsBottom().intersects(t.getBounds())) {
					setVelY(0);
					if (falling) {
						falling = false;
					}
				} else if (!falling && !jumping) {
					gravity = 0.0;
					falling = true;
				}

				if (getBoundsLeft().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX() + t.width;
				}

				if (getBoundsRight().intersects(t.getBounds())) {
					setVelX(0);
					x = t.getX() - t.width;
				}
			}
		}

		if (jumping) {
			gravity -= 0.1;
			setVelY((int) -gravity);

			if (gravity <= 0.0) {
				jumping = false;
				falling = true;
			}
		}

		if (falling) {
			gravity += 0.1;
			setVelY((int) gravity);
		}

		if (animate) {

			frameDelay++;

			if (frameDelay >= 10) {
				frame++;
				if (frame >= 4) {
					frame = 0;
				}
				frameDelay = 0;
			}
		}
	}
}
