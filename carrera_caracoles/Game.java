package carrera_caracoles;

import javajuegojavaswing_parte11.Ball;
import javajuegojavaswing_parte11.Racquet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Game extends JPanel {

	static Caracol[] caracol = new Caracol[4];
	static boolean hasStarted = false;
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE && !hasStarted) {
					hasStarted = true;
					for (int i=0; i<caracol.length; i++) {
						caracol[i] = new Caracol(i+1);
					}
					Caracol.iniciarCarrrera();
					for (Caracol caracol1 : caracol) {
						caracol1.start();
					}
				}
				else if (hasStarted && !Caracol.haTerminado()) {
					if (Thread.currentThread().isInterrupted())
						Thread.currentThread().start();
					else
						Thread.currentThread().interrupt();
				}
			}
		});
		setFocusable(true);
	}
	
	private void move() throws InterruptedException {
		for (Caracol caracol1 : caracol) {
			caracol1.move();
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Arial", Font.BOLD, 30));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.black);
		g2d.fillRect(88, 0, 4, 200);
		g2d.fillRect(712, 0, 4, 200);
		for (Caracol caracol1 : caracol) {
			caracol1.paint(g2d);
		}
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i=0; i<caracol.length; i++) {
			caracol[i] = new Caracol(i+1);
		}
		JFrame frame = new JFrame("Carrera Caracoles");
		Game game = new Game();
		frame.add(game);
		frame.setSize(800, 200);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		while (true) {
			while (hasStarted && !Caracol.haTerminado()) {
				game.move();
				game.repaint();
			}
			if (hasStarted) {
				hasStarted = false;
			}
			Thread.sleep(100);
		}
	}
}
