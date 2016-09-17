package ua.kas.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.MenuParticle;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 640;
	public static final int HEIGHT = WIDTH / 12 * 9;

	private boolean running = false;

	private Thread thread;

	private Random random;
	private HUD hud;
	private Handler handler;
	private Spawn spawn;
	private Menu menu;

	public enum STATE {
		Menu, Game, Help, End
	};

	public static STATE gameState = STATE.Menu;

	public Game() {
		random = new Random();
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(handler, hud);
		spawn = new Spawn(handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Window(WIDTH, HEIGHT, "Meteors", this);

		if (gameState == STATE.Game) {

		} else {
			for (int i = 0; i < 10; i++) {
				handler.addObject(new MenuParticle(random.nextInt(WIDTH - 50), random.nextInt(HEIGHT - 50),
						ObjectId.MenuParticle, handler));
			}
		}

	}

	public synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;

		double updates = 0;
		double frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
				updates++;
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
		stop();
	}

	private void tick() {
		handler.tick();
		if (gameState == STATE.Game) {
			hud.tick();
			spawn.tick();
			if (HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				spawn.setScoreKeep(0);
				gameState = STATE.End;
				handler.clearEnemy();
				for (int i = 0; i < 10; i++) {
					handler.addObject(new MenuParticle(random.nextInt(WIDTH - 50), random.nextInt(HEIGHT - 50),
							ObjectId.MenuParticle, handler));
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help) {
			menu.render(g);
		} else if (gameState == STATE.End) {
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}
	}

	public static void main(String[] args) {
		new Game();
	}
}