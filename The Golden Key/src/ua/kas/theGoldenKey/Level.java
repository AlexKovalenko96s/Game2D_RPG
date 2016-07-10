package ua.kas.theGoldenKey;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	public int width = 100, height = 100;

	public Background[][] bg = new Background[width][height];
	public Solid[][] solid = new Solid[width][height];
	public Item[][] item = new Item[width][height];

	public final String Dpath = "res/World/level_";
	public String path = Dpath;

	public TiledMap map = null;

	public Level(int id) {
		path = Dpath + Integer.toString(id) + ".tmx";
		System.out.println(path);

		try {
			map = new TiledMap(path, false);
		} catch (SlickException e) {
			System.err.println("Error Loading Map!");
		}

		for (int x = 0; x < bg.length; x++) {
			for (int y = 0; y < bg[0].length; y++) {
				bg[x][y] = new Background(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size),
						Tile.blank);
				solid[x][y] = new Solid(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size), Tile.blank);
				item[x][y] = new Item(new Rectangle(x * Tile.size, y * Tile.size, Tile.size, Tile.size), Tile.blank);
			}
		}

		loadWorld();

	}

	public void loadWorld() {
		int background = map.getLayerIndex("background");
		int solids = map.getLayerIndex("collision");
		int items = map.getLayerIndex("object");

		for (int x = 0; x < bg.length; x++) {
			for (int y = 0; y < bg[0].length; y++) {

				// background
				if (map.getTileId(x, y, background) == 1) {
					bg[x][y].id = Tile.grass;
				}

				if (map.getTileId(x, y, background) == 2) {
					bg[x][y].id = Tile.road;
				}

				if (map.getTileId(x, y, background) == 3) {
					bg[x][y].id = Tile.leaves;
				}

				// solids

				if (map.getTileId(x, y, solids) == 65) {
					solid[x][y].id = Tile.grass;
				}

				// items

				if (map.getTileId(x, y, items) == 129) {
					item[x][y].id = Tile.grass;
				}
			}
		}
	}

	public void tick() {

	}

	public void render(Graphics g, int camX, int camY, int renX, int renY) {

		for (int x = (camX / Tile.size); x < (camX / Tile.size) + renX; x++) {
			for (int y = (camY / Tile.size); y < (camY / Tile.size) + renY; y++) {
				if (x >= 0 && y >= 0 && x < width && y < height) {
					bg[x][y].render(g);
				}
			}
		}
	}
}
