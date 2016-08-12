package ua.kas.main.object;

import java.awt.Graphics;

public interface Entity {

	public void tick();

	public void render(Graphics g);

	public double getX();

	public double getY();
}
