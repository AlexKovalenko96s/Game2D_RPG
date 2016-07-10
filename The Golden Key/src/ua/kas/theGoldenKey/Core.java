package ua.kas.theGoldenKey;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public class Core extends Applet implements Runnable {

	private static final long serialVersionUID = 1L;

	public final int TARGET_FPS = 60;
	public final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	public static long lastFpsTime = 0;
	public static int fps = 0;
	public static int renderFps = 0;

	private static JFrame frame;

	public static final int res = 1;

	public static double oY = 0, oX = 0;

	public static int dir = 0;
	public int targetFPS = 5;

	public static boolean moving = false;
	public static boolean run = false;

	private Image screen;

	public static Player player;
	public Level level;

	public static Dimension screenSize = new Dimension(700, 560);
	public static Dimension pixel = new Dimension(screenSize.width, screenSize.height);
	public static Dimension Size;

	public static String name = "The Golden Key";

	public Core() {
		setPreferredSize(screenSize);
		addKeyListener(new InputManager());
	}

	public static void main(String[] args) {
		Core core = new Core();

		frame = new JFrame();
		frame.add(core);
		frame.pack();

		Size = new Dimension(frame.getWidth(), frame.getHeight());

		frame.setTitle(name);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		core.start();
	}

	public void start() {
		requestFocus();

		// define classes
		level = new Level(1);
		player = new Player("Alex");
		new Tile();

		run = true;
		new Thread(this).start();
	}

	public void stop() {
		run = false;
	}

	public void tick(double delta) {

		frame.pack();

		player.tick(delta);
		level.tick(delta);
	}

	public void render() {

		Graphics g = screen.getGraphics();

		level.render(g, (int) oX, (int) oY, (pixel.width / Tile.size) + 2, (pixel.height / Tile.size) + 2);

		player.render(g);

		g.setColor(Color.RED);
		g.drawString("oX: " + (int) oX + " oY: " + (int) oY, 600, 515);
		g.drawString("FPS: " + renderFps, 600, 530);

		g = this.getGraphics();
		g.drawImage(screen, 0, 0, screenSize.width, screenSize.height, 0, 0, pixel.width, pixel.height, null);

		g.dispose();
	}

	@Override
	public void run() {

		screen = createVolatileImage(pixel.width, pixel.height);
		long lastLoopTime = System.nanoTime();

		while (run) {

			long now = System.nanoTime();
			long updateLenght = now - lastLoopTime;
			lastLoopTime = now;

			double delta = updateLenght / (double) OPTIMAL_TIME;
			lastFpsTime += updateLenght;
			fps++;

			if (lastFpsTime >= 1000000000) {
				renderFps = fps;
				fps = 0;
				lastFpsTime = 0;
			}

			tick(delta);
			render();

			try {
				Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
			} catch (Exception e) {
				// System.out.println("Sleeping thread Error!");
			}

			// long elapsed = System.nanoTime();
			// long wait = (1000 / targetFPS) - elapsed / 1000000;
			//
			// if (wait < 0) {
			// wait = 5;
			// }
			//
			// try {
			// Thread.sleep(wait);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}
}
