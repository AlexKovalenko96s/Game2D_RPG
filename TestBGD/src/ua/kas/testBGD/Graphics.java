package ua.kas.testBGD;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import ua.kas.testBGD.objects.Enemy;
import ua.kas.testBGD.objects.Player;

public class Graphics extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private Timer timer;

	public Player player;
	public Enemy enemy;
	public Controller controller;

	public Graphics() {
		setFocusable(true);
		timer = new Timer(10, this);
		timer.start();
		player = new Player(100, 100);
		controller = new Controller();
		addKeyListener(player);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		controller.update();
		player.update();
	}

	@Override
	protected void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g.drawImage(getBackgroundTile(), 0, 0, null);
		player.draw(g);
		controller.draw(g);
	}

	public Image getBackgroundTile() {
		ImageIcon i = new ImageIcon();
		try {
			i = new ImageIcon(ImageIO.read(new File("res/grass.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return i.getImage();
	}
}
