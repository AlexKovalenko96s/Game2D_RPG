package ua.kas.superMario.tile;

import java.awt.Color;
import java.awt.Graphics;

import ua.kas.superMario.Handler;
import ua.kas.superMario.Id;

public class Wall extends Tile {

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	@Override
	public void render(Graphics g) {

		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void tick() {

	}

}
