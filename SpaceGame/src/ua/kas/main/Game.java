package ua.kas.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;

	public static final String TITLE = "2D SpaceGame";

	private String spriteSheetPath = "res/spaceGame.png";

	private boolean running = false;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage player_img = null;

	private Thread thread;

	public SpriteSheet spriteSheet;
	public Player player;

	public void init() {
		spriteSheet = new SpriteSheet(spriteSheetPath);
		player_img = (BufferedImage) spriteSheet.getShip();

		addKeyListener(new KeyInput(this));

		player = new Player((WIDTH * SCALE) / 2, ((HEIGHT * SCALE) / 6) * 5, this);
	}

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

		System.exit(1);
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
			// game loop
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

	private void tick() {

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		player.render(g);
		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			player.setY(player.getY() - 5);
		}
		if (key == KeyEvent.VK_S) {
			player.setY(player.getY() + 5);
		}
		if (key == KeyEvent.VK_A) {
			player.setX(player.getX() - 5);
		}
		if (key == KeyEvent.VK_D) {
			player.setX(player.getX() + 5);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		// if (key == KeyEvent.VK_W) {
		// player.setY(0);
		// }
		// if (key == KeyEvent.VK_S) {
		// player.setY(0);
		// }
		// if (key == KeyEvent.VK_A) {
		// player.setX(0);
		// }
		// if (key == KeyEvent.VK_D) {
		// player.setX(0);
		// }
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
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

	public BufferedImage getPlayer_img() {
		return player_img;
	}
}