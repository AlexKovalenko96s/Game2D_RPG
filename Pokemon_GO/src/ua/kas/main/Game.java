package ua.kas.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ua.kas.main.object.Enemy;
import ua.kas.main.object.Player;
import ua.kas.main.object.Shot;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;

	private static final String TITLE = "Pokemon_GO";

	private boolean running = false;

	private Thread thread;

	private BufferedImage background_img = null;

	private SpriteSheet spriteSheet;
	private Player player;
	private Shot shot;
	private Enemy enemy;

	private synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
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
		System.exit(0);
	}

	public void init() {
		spriteSheet = new SpriteSheet("res/spriteSheel.png");

		try {
			background_img = ImageIO.read(new File("res/background.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// not used
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_A) {
					player.setVelX(0);
				}

				if (key == KeyEvent.VK_D) {
					player.setVelX(0);
				}

				if (key == KeyEvent.VK_SPACE) {

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_A) {
					player.setVelX(-5);
				}

				if (key == KeyEvent.VK_D) {
					player.setVelX(+5);
				}

				if (key == KeyEvent.VK_SPACE) {

				}
			}
		});

		player = new Player(((WIDTH * SCALE) / 2), ((HEIGHT * SCALE) / 8) * 7, spriteSheet);
	}

	@Override
	public void run() {
		init();
		requestFocus();

		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Ticks - " + updates + ", FPS - " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void tick() {
		player.tick();
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(background_img, 0, 0, null);
		player.render(g);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

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
