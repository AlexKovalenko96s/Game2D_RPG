package ua.kas.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import ua.kas.main.entity.EntityA;
import ua.kas.main.entity.EntityB;
import ua.kas.main.object.Player;
import ua.kas.main.object.Shot;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 2;

	public int enemy_count = 3;
	public int enemy_killed = 0;
	public int health = 100;
	public int score = 0;

	private static final String TITLE = "Pokemon_GO";

	private boolean running = false;
	private boolean shooting = false;
	private boolean dead = false;

	private Thread thread;

	private BufferedImage background_img = null;
	private BufferedImage menuBackground_img = null;
	private BufferedImage logoInGame_img = null;

	public ArrayList<EntityA> entityA;
	public ArrayList<EntityB> entityB;

	private Controller controller;
	private SpriteSheet spriteSheet;
	private Player player;
	private Menu menu;

	public static enum STATE {
		MENU, GAME
	}

	public static STATE state = STATE.MENU;

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

		// playSound();

		// AudioInputStream inputStream;
		// try {
		// inputStream = AudioSystem.getAudioInputStream(new
		// File("res/PokemonRuOST.wav"));
		// Clip clip = AudioSystem.getClip();
		// clip.open(inputStream);
		// clip.loop(Clip.LOOP_CONTINUOUSLY);
		// Thread.sleep(10000);
		// } catch (UnsupportedAudioFileException | IOException |
		// LineUnavailableException | InterruptedException e1) {
		// e1.printStackTrace();
		// }

		try {
			background_img = ImageIO.read(new File("res/background.png"));
			logoInGame_img = ImageIO.read(new File("res/logoInGame.png"));
			menuBackground_img = ImageIO.read(new File("res/menuBackground.png"));
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
					if (shooting && !dead) {
						shooting = false;
					}
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
					if (!shooting && !dead) {
						controller.addEntity(new Shot(player.getX(), player.getY() - 32, spriteSheet));
						shooting = true;
					}
					if (dead) {
						dead = false;
						score = 0;
						health = 100;
						enemy_count = 3;
						enemy_killed = 0;
						controller.clearAllEntity();
						controller.createEnemy(enemy_count);
					}
				}

				if (key == KeyEvent.VK_ESCAPE) {
					if (state == STATE.MENU) {
						System.exit(0);
					}
					if (state == STATE.GAME) {
						state = STATE.MENU;
						dead = false;
						score = 0;
						health = 100;
						enemy_count = 3;
						enemy_killed = 0;
						controller.clearAllEntity();
						controller.createEnemy(enemy_count);
					}
				}
			}
		});

		this.addMouseListener(new MouseInput());

		controller = new Controller(spriteSheet, this);
		player = new Player(((WIDTH * SCALE) / 2), ((HEIGHT * SCALE) / 8) * 7, spriteSheet, this, controller);
		menu = new Menu();

		entityA = controller.getAl_entityA();
		entityB = controller.getAl_entityB();

		controller.createEnemy(enemy_count);
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
		if (state == STATE.GAME) {
			player.tick();
			controller.tick();
		}

		if (enemy_killed >= enemy_count) {
			enemy_count++;
			enemy_killed = 0;
			controller.createEnemy(enemy_count);
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		if (health <= 0) {
			dead = true;
		}

		if (!dead && state == STATE.GAME) {
			g.drawImage(background_img, 0, 0, null);
			player.render(g);
			controller.render(g);

			g.setColor(new Color(207, 202, 215));
			g.fillRect(0, 0, WIDTH * SCALE + 20, 40);

			g.drawImage(logoInGame_img, WIDTH + 100, 2, null);

			g.setColor(Color.WHITE);
			g.fill3DRect(5, 5, 100 * SCALE, 30, true);

			g.setColor(Color.GREEN);
			g.fill3DRect(5, 5, health * SCALE, 30, true);

			g.setColor(Color.RED);
			g.drawRect(5, 5, 100 * SCALE, 30);

			Font font = new Font("arial", Font.BOLD, 20);
			g.setFont(font);
			g.drawString("Score : " + score, WIDTH - 100, 28);
			g.drawString("HP " + health, 10, 28);
		}

		if (dead && state == STATE.GAME) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, WIDTH * SCALE + 20, HEIGHT * SCALE + 20);
			g.setColor(Color.WHITE);
			Font fontDead = new Font("arial", Font.BOLD, 50);
			g.setFont(fontDead);
			g.drawString("GAME OVER!", WIDTH - 150, HEIGHT + 10);
			fontDead = new Font("arial", Font.BOLD, 20);
			g.setFont(fontDead);
			g.drawString("Press <<Space>> for restart or <<Esc>> for exit", WIDTH - 220, HEIGHT + 40);
		}

		if (state == STATE.MENU) {
			g.drawImage(menuBackground_img, 0, 0, null);
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	// public static synchronized void playSound() {
	// new Thread(new Runnable() {
	// // The wrapper thread is unnecessary, unless it blocks on the
	// // Clip finishing; see comments.
	// public void run() {
	// try {
	// Clip clip = AudioSystem.getClip();
	// AudioInputStream inputStream = AudioSystem.getAudioInputStream(new
	// File("res/PokemonRuOST.mp3"));
	// clip.open(inputStream);
	// clip.start();
	// } catch (Exception e) {
	// System.err.println(e.getMessage());
	// }
	// }
	// }).start();
	// }

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

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public int getScore() {
		return score;
	}
}
