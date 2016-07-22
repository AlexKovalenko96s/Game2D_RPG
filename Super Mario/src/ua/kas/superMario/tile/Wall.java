package ua.kas.superMario.tile;

import java.awt.Graphics;

import ua.kas.superMario.Handler;
import ua.kas.superMario.Id;
import ua.kas.superMario.Main;

public class Wall extends Tile {

	public Wall(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);

	}

	@Override
	public void render(Graphics g) {

		g.drawImage(Main.grass.getBufferedImage(), x, y, width, height, null);

		// g.setColor(Color.RED); simple rectangle
		// g.fillRect(x, y, width, height);
	}

	@Override
	public void tick() {

	}

}
