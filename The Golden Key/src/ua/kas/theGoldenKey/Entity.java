package ua.kas.theGoldenKey;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity {

	/** entity coordinates **/
	protected double x, y;
	/** entity`s width and height **/
	protected int width, height;
	/** entity`s moveSpeed **/
	protected double moveSpeed;
	/** entity`s health **/
	protected double health;
	/** entity`s tile position in tile set **/
	protected int[] id;
	/** image for rendering entity **/
	protected Image image;

	private Rectangle me = new Rectangle();
	private Rectangle him = new Rectangle();

	public Entity(int[] i, double x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		moveSpeed = 1.8;
		health = 100;
		me = new Rectangle((int) x, (int) y, width, height);
	}

	public void move(int delta, int dx, int dy) {
		x += (moveSpeed * delta) * dx;
		y += (moveSpeed * delta) * dy;
	}

	public void setImage(int[] i) {
		image = Tile.characters.getSubimage(i[0] * Tile.size, i[1] * Tile.size, width, height);
	}

	public void render(Graphics g) {
		setImage(id);
		g.drawImage(image, (int) (x - Core.oX), (int) (y - Core.oY), null);
	}

	public boolean colidesWith(Entity entity) {

		him.setBounds((int) entity.x, (int) entity.y, entity.width, entity.height);

		// if(me.intersects(him)){
		// return true;
		// }
		//
		// return false;

		return me.intersects(him);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getWight() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public double getHealth() {
		return health;
	}

	public abstract void collidedWith(Entity entity);
}