package javajuegojavaswing_parte11;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private static final int Y = 100;
	private static final int WITH = 300;
	private static final int HEIGHT = 10;
	int x = 0;
	int xa = 0;
	int y = 0;
	private Game game;

	public Racquet(Game game, int y) {
		this.game = game;
		this.y = y;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.fillRect(x, y, WITH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -2;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = 2;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, WITH, HEIGHT);
	}

	public int getTopY() {
		return y - HEIGHT;
	}
}

