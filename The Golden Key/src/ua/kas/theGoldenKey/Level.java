package ua.kas.theGoldenKey;

import java.awt.Rectangle;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Level {

	public int width = 100, height = 100;

	public Background[][] bg = new Background[width][height];

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
			}
		}
	}
}
