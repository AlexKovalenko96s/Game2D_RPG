package ua.kas.superMario.entity;

import java.awt.Color;
import java.awt.Graphics;

import ua.kas.superMario.Handler;
import ua.kas.superMario.Id;

public class Player extends Entity {

	public Player(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {

		super(x, y, width, height, solid, id, handler);

	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void tick() {

		x += velX;
		y += velY;

		if (x <= 0) {
			x = 0;
		}

		if (y <= 0) {
			y = 0;
		}

		if (x + width >= 1090) {
			x = 1090 - width;
		}

		if (y + height >= 771) {
			y = 771 - height;
		}
	}
}
