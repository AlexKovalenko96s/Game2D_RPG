package ua.kas.superMario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ua.kas.superMario.entity.Entity;
import ua.kas.superMario.gfx.Sprite;
import ua.kas.superMario.gfx.SpriteSheet;
import ua.kas.superMario.input.KeyInput;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH / 14 * 10;
	public static final int SCALE = 4;

	public static final String TITLE = "My Super Mario";

	private boolean running = false;

	private Thread thread;

	public static Handler handler;
	public static SpriteSheet sheet;
	public static Camera cam;

	public static Sprite grass;
	public static Sprite player[] = new Sprite[8];

	private BufferedImage image;

	public Main() {

		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
	}

	private void init() {

		cam = new Camera();
		handler = new Handler();
		sheet = new SpriteSheet("/spritesheet.png");

		addKeyListener(new KeyInput());

		grass = new Sprite(sheet, 2, 1);

		int n = 0;

		for (int k = 1; k <= 2; k++) {
			for (int i = 6; i <= 9; i++) {

				player[n] = new Sprite(sheet, i, k);
				n++;

			}
		}

		try {
			image = ImageIO.read(getClass().getResource("/level.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		handler.createLevel(image);

		// handler.addEntity(new Player(370, 506, 64, 64, true, Id.Player,
		// handler));
		// handler.addTile(new Wall(200, 200, 64, 64, true, Id.Wall, handler));
	}

	private synchronized void start() {

		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this, "Thread");
		thread.start();
	}

	private synchronized void stop() {

		if (!running) {
			return;
		}

		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		init();

		requestFocus();

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();

		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;

		int frames = 0;
		int ticks = 0;

		while (running) {

			long now = System.nanoTime();

			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {

				tick();

				ticks++;
				delta--;

			}

			render();

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {

				timer += 1000;
				System.out.println(frames + " Frames Per Second " + ticks + " Updates Per Second");

				frames = 0;
				ticks = 0;
			}
		}

		stop();
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(140, 190, 255));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(cam.getX(), cam.getY());

		// g.setColor(Color.YELLOW);
		// g.fillRect(200, 200, getWidth() - 400, getHeight() - 400);
		//
		// g.setColor(Color.GREEN);// Collision Detection
		// g.fillRect(371, 506, 64 - 2, 5);// Collision Detection
		// g.fillRect(370, 507, 5, 64 - 2);// Collision Detection

		handler.render(g);

		g.dispose();
		bs.show();
	}

	public void tick() {
		handler.tick();

		for (Entity e : handler.entity) {
			if (e.getId() == Id.Player) {
				cam.tick(e);
			}
		}
	}

	public int getFrameWidth() {
		return WIDTH * SCALE;
	}

	public int getFrameHeight() {
		return HEIGHT * SCALE;
	}

	public static void main(String[] args) {

		Main main = new Main();

		JFrame frame = new JFrame(TITLE);

		frame.add(main);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		main.start();
		frame.setVisible(true);

	}
}