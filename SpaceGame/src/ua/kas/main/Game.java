package ua.kas.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ua.kas.main.classes.EntityA;
import ua.kas.main.classes.EntityB;
import ua.kas.main.object.Bullet;
import ua.kas.main.object.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;

	public static final String TITLE = "2D SpaceGame";

	private String spriteSheetPath = "res/spaceGame.png";
	private String backgroundPath = "res/bg_simple.png";

	private int enemy_count = 3;
	private int enemy_killed = 0;

	private boolean running = false;
	private boolean shooting = false;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;

	private BufferedImage background_img = null;

	private Thread thread;

	public SpriteSheet spriteSheet;
	public Player player;
	public Controller controller;

	public void init() {
		spriteSheet = new SpriteSheet(spriteSheetPath);

		try {
			background_img = ImageIO.read(new File(backgroundPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.addKeyListener(new KeyInput(this));

		player = new Player((WIDTH * SCALE) / 2, ((HEIGHT * SCALE) / 6) * 5, spriteSheet, this);
		controller = new Controller(spriteSheet, this);

		ea = controller.getEa();
		eb = controller.getEb();

		controller.createEnemy(enemy_count);
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
		player.tick();
		controller.tick();

		if (enemy_killed >= enemy_count) {
			enemy_count += 1;
			enemy_killed = 0;
			controller.createEnemy(enemy_count);
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(background_img, 0, 0, null);
		player.render(g);
		controller.render(g);
		g.dispose();
		bs.show();
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			player.setVelY(-5);
		}
		if (key == KeyEvent.VK_S) {
			player.setVelY(+5);
		}
		if (key == KeyEvent.VK_A) {
			player.setVelX(-5);
		}
		if (key == KeyEvent.VK_D) {
			player.setVelX(+5);
		}
		if (key == KeyEvent.VK_SPACE && !shooting) {
			shooting = true;
			controller.addEntity(new Bullet(player.getX(), player.getY() - 32, spriteSheet, this));
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W) {
			player.setVelY(0);
		}
		if (key == KeyEvent.VK_S) {
			player.setVelY(0);
		}
		if (key == KeyEvent.VK_A) {
			player.setVelX(0);
		}
		if (key == KeyEvent.VK_D) {
			player.setVelX(0);
		}
		if (key == KeyEvent.VK_SPACE) {
			shooting = false;
		}
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

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
}