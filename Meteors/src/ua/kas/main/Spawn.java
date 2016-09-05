package ua.kas.main;

import java.util.Random;

import ua.kas.main.framework.ObjectId;
import ua.kas.main.object.BasicEnemy;
import ua.kas.main.object.FastEnemy;

public class Spawn {

	private Random random;
	private Handler handler;
	private HUD hud;

	private int scoreKeep = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		random = new Random();
	}

	public void tick() {
		scoreKeep++;
		if (scoreKeep >= 1000) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);

			if (hud.getLevel() == 2) {
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
						ObjectId.BasicEnemy, handler));
			} else if (hud.getLevel() == 3) {
				handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
						ObjectId.BasicEnemy, handler));
			} else if (hud.getLevel() == 4) {
				handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH - 50), random.nextInt(Game.HEIGHT - 50),
						ObjectId.FastEnemy, handler));
			}
		}
	}
}