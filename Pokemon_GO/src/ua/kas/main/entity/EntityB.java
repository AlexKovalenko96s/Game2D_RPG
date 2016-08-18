package ua.kas.main.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityB {

	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();
}
