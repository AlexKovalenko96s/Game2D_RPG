package ua.kas.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.Block;
import ua.kas.main.object.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 360;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;

	private static final String TITLE = "Neon Platform Game Prototype";

	private boolean running = false;

	private BufferedImage level = null;
	private BufferedImage background = null;

	private Thread thread;

	public Camera camera;
	public Handler handler;
	public BufferedImageLoader loader;
	public Texture texture;

	private void init() {
		loader = new BufferedImageLoader();
		background = loader.loadImage("/background.png");
		level = loader.loadImage("/level.png");
		texture = new Texture();
		handler = new Handler();

		camera = new Camera(0, 0);

		loadImageLevel(level);

		this.addKeyListener(new KeyInput(handler));
	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		requestFocus();
		init();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				System.out.println("Ticks: " + updates + " FPS: " + frames);
				timer += 1000;
				updates = 0;
				frames = 0;
			}
		}
	}

	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				camera.tick(handler.object.get(i));
			}
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(background, 0, 0, null);
		g2d.translate(camera.getX(), camera.getY());// begin of camera

		handler.render(g);

		// g2d.translate(-camera.getX(), -camera.getY());// end of camera

		g.dispose();
		bs.show();
	}

	private void loadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < h; xx++) {
			for (int yy = 0; yy < w; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0) {
					handler.addObject(new Block(xx * 32, yy * 32, ObjectId.Block, this, 0));
				}
				if (red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Player(xx * 32, yy * 32, ObjectId.Player, handler, this));
				}
				if (red == 38 && green == 127 && blue == 0) {
					handler.addObject(new Block(xx * 32, yy * 32, ObjectId.Block, this, 1));
				}
			}
		}
	}

	public Texture getInstance() {
		return texture;
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		game.start();
	}
}