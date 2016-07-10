package ua.kas.theGoldenKey;

import java.awt.Rectangle;

public class Player extends Rectangle {

	private static final long serialVersionUID = 1L;

	public double moveSpeed = 1.0;

	public static boolean isMoving = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean left = false;
	public static boolean right = false;

	public int aniFrame = 1;
	public int aniDelta = 0;

	public Player(String name) {
		width = 32;
		height = 32;
		setBounds((Core.pixel.width / 2) - (width / 2), (Core.pixel.height / 2) - (height / 2), width, height);
	}

	public void tick() {

	}
}
