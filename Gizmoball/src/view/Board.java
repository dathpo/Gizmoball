package view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import model.IAbsorber;
import model.IBall;
import model.IBumper;
import model.IFlipper;
import model.IModel;
import model.LFlipper;
import model.Model;
import model.RFlipper;
import physics.Circle;
import physics.LineSegment;

public class Board extends JPanel implements Observer {

	protected static int width;
	protected static int height;
	private IModel model;
	protected static final int L = 20;;

	public Board(int w, int h, Model m) {
		m.addObserver(this);
		this.model = m;
		width = w;
		height = h;
		model = m;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Graphics2D g2 = (Graphics2D) g;
		paintGrid(g);
		paintBall(g);
		paintBumpers(g);
		paintAbsorbers(g);
		paintFlippers(g);
		repaint();
	}

	public void paintGrid(Graphics g) {
		for (int k = 0; k <= L; k++) {
			g.drawLine(0, k * height / L, width, k * height / L);
		}
		for (int k = 0; k <= L; k++) {
			g.drawLine(k * width / L, 0, k * width / L, height);
		}
	}

	public void paintBall(Graphics g) {
		if (model.getBalls() != null) {
			IBall ball = model.getBalls();
			g.setColor(ball.getColour());
			int radius = (int) (ball.getRadius());
			int diameter = (int) (2 * radius);
			int xB = (int) (ball.getX() - radius);
			int yB = (int) (ball.getY() - radius);
			g.fillOval(xB, yB, diameter, diameter);
		}
	}

	public void paintBumpers(Graphics g) {
		if (model.getBumpers() != null) {
			for (IBumper bumper : model.getBumpers()) {
				List<Circle> circles = bumper.getCircles();
				List<LineSegment> lineSegments = bumper.getLineSegments();
				g.setColor(bumper.getColour());
				if (circles.size() > lineSegments.size()) {
					int radius = (int) (circles.get(0).getRadius());
					int diameter = (int) (2 * radius);
					int xC = (int) (circles.get(0).getCenter().x() - radius);
					int yC = (int) (circles.get(0).getCenter().y() - radius);
					g.fillOval(xC, yC, diameter, diameter);
				} else if ((lineSegments.size() & circles.size()) == 3) {
					int[] xT = new int[3];
					int[] yT = new int[3];
					int lengthS = circles.size();
					for (int i = 0; i < 3; i++) {
						xT[i] = (int) circles.get(i).getCenter().x();
						yT[i] = (int) circles.get(i).getCenter().y();
					}
					g.fillPolygon(xT, yT, lengthS);
				} else if ((lineSegments.size() & circles.size()) == 4) {
					int xS = 0;
					int yS = 0;
					int lengthS = (int) lineSegments.get(0).length();
					for (int i = 0; i < 4; i++) {
						xS = (int) (circles.get(i).getCenter().x() - (int) lineSegments.get(i).length());
						yS = (int) (circles.get(i).getCenter().y() - (int) lineSegments.get(i).length());
					}
					g.fillRect(xS, yS, lengthS, lengthS);
				}
			}
		}
	}

	public void paintAbsorbers(Graphics g) {
		if (model.getAbsorbers() != null) {
			for (IAbsorber absorber : model.getAbsorbers()) {
				List<Circle> circles = absorber.getCircles();
				List<LineSegment> lineSegments = absorber.getLineSegments();
				g.setColor(absorber.getColour());
				if ((lineSegments.size() & circles.size()) == 4) {
					int xA1 = (int) (circles.get(0).getCenter().x());
					// System.out.println("xA1: " + ((int)
					// (circles.get(0).getCenter().x())/20));
					int yA1 = (int) (circles.get(0).getCenter().y());
					// System.out.println("yA1: " + ((int)
					// (circles.get(0).getCenter().y())/20));
					int width = (int) (circles.get(3).getCenter().x() - (circles.get(0).getCenter().x()));
					// System.out.println("width: " + ((int)
					// (circles.get(3).getCenter().x() -
					// (circles.get(0).getCenter().x()))/20));
					int height = (int) (circles.get(3).getCenter().y() - (circles.get(0).getCenter().y()));
					// System.out.println("height: " + ((int)
					// (circles.get(3).getCenter().y() -
					// (circles.get(0).getCenter().y()))/20));
					g.fillRect(xA1, yA1, width, height);
				}
			}
		}
	}

	// public void paintLFlippers(Graphics g) {
	// if (model.getLFlippers() != null) {
	// for (LFlipper flipper : model.getLFlippers()) {
	// g.setColor(flipper.getColour());
	// int xF = (int) flipper.getX();
	// int yF = (int) flipper.getY();
	// int length = flipper.getLength();
	// g.fillRoundRect(xF, yF, (length / 2), length * 2, (length / 4), (length /
	// 2));
	// }
	// }
	// }

	// public void paintRFlippers(Graphics g) {
	// if (model.getRFlippers() != null) {
	// for (RFlipper flipper : model.getRFlippers()) {
	// g.setColor(flipper.getColour());
	// int length = flipper.getLength();
	// int xF = (int) flipper.getX() + (2*length)-(length/2);
	// int yF = (int) flipper.getY();
	// g.fillRoundRect(xF, yF, (length / 2), length * 2, (length / 4), (length /
	// 2));
	// }
	// }
	// }

	public void paintFlippers(Graphics g) {
		paintFlipperL(g);
		paintFlipperR(g);
	}
	
	public void paintFlipperL(Graphics g) {
		if (model.getFlippers() != null) {
			for (IFlipper flipper : model.getFlippers()) {
				if (flipper.getRight() == false) {
					int xF = (int) flipper.getX();
					int yF = (int) flipper.getY();
					int length = flipper.getLength();

					if (flipper.getRotated() == false) {
						g.setColor(flipper.getColour());
						g.fillRoundRect(xF, yF, (length / 4), length, (length / 8), (length / 4));
					} else if (flipper.getRotated() == true) {
						g.setColor(flipper.getColour());
						g.fillRoundRect(xF, yF, length, length / 4, (length / 8), (length / 4));

					}
				}
			}
		}
	}

	public void paintFlipperR(Graphics g) {
		if (model.getFlippers() != null) {
			for (IFlipper flipper : model.getFlippers()) {
				if (flipper.getRight() == true) {
					int xF = (int) flipper.getX();
					int yF = (int) flipper.getY();
					int length = flipper.getLength();

					if (flipper.getRotated() == false) {
						g.setColor(flipper.getColour());
						g.fillRoundRect(xF, yF, (length / 4), length, (length / 8), (length / 4));
					} else if (flipper.getRotated() == true) {
						g.setColor(flipper.getColour());
						g.fillRoundRect(xF - (length * 3 / 4), yF, length, length / 4, (length / 8), (length / 4));
					}
				}
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}