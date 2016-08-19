package ua.kas.main.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import ua.kas.main.SpriteSheet;
import ua.kas.main.entity.EntityA;
import ua.kas.main.libs.Animation;

public class Shot extends GameObject implements EntityA {

	public SpriteSheet spriteSheet;
	public Animation animation;

	public Shot(double x, double y, SpriteSheet spriteSheet) {
		super(x, y);
		this.spriteSheet = spriteSheet;
		animation = new Animation(5, spriteSheet.shot[0], spriteSheet.shot[1], spriteSheet.shot[2],
				spriteSheet.shot[3]);
	}

	@Override
	public void tick() {
		y -= 10;
		animation.runAnimation();
	}

	@Override
	public void render(Graphics g) {
		animation.drawAnimation(g, x, y + 32 * 2, 0);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y + 32 * 2, 32, 32);
	}
}