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
import physics.Circle;
import physics.LineSegment;

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected static int width;
	protected static int height;
	private IModel model;
	protected static final int L = 20;

	public Board(int w, int h, IModel m) {
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
		if (model.getBall() != null) {
			IBall ball = model.getBall();
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
					int lengthT = circles.size();
					for (int i = 0; i < 3; i++) {
						xT[i] = (int) circles.get(i).getCenter().x();
						yT[i] = (int) circles.get(i).getCenter().y();
					}
					g.fillPolygon(xT, yT, lengthT);
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
					int yA1 = (int) (circles.get(0).getCenter().y());
					int width = (int) (circles.get(3).getCenter().x() - (circles.get(0).getCenter().x()));
					int height = (int) (circles.get(3).getCenter().y() - (circles.get(0).getCenter().y()));
					g.fillRect(xA1, yA1, width, height);
				}
			}
		}
	}

	public void paintFlippers(Graphics g) {
		if (model.getFlippers() != null) {
			for (IFlipper flipper : model.getFlippers()) {
				flipper.getCircles();
				flipper.getLineSegments();
				g.setColor(flipper.getColour());
				if (!flipper.isDeleted()) {
					int circleRadius = flipper.getLength() / 8;
					int circleDiameter = flipper.getLength() / 4;
					int pivotX = (int) flipper.getCircles().get(0).getCenter().x() - circleRadius;
					int pivotY = (int) flipper.getCircles().get(0).getCenter().y() - circleRadius;
					g.fillOval(pivotX, pivotY, circleDiameter, circleDiameter);

					int outerCX = (int) flipper.getCircles().get(1).getCenter().x() - circleRadius;
					int outerCY = (int) flipper.getCircles().get(1).getCenter().y() - circleRadius;
					g.fillOval(outerCX, outerCY, circleDiameter, circleDiameter);

					int[] xF = new int[4];
					int[] xY = new int[4];

					xF[0] = (int) (flipper.getLineSegments().get(0).p1().x());
					xF[1] = (int) (flipper.getLineSegments().get(0).p2().x());
					xF[2] = (int) (flipper.getLineSegments().get(1).p2().x());
					xF[3] = (int) (flipper.getLineSegments().get(1).p1().x());

					xY[0] = (int) (flipper.getLineSegments().get(0).p1().y());
					xY[1] = (int) (flipper.getLineSegments().get(0).p2().y());
					xY[2] = (int) (flipper.getLineSegments().get(1).p2().y());
					xY[3] = (int) (flipper.getLineSegments().get(1).p1().y());
					g.fillPolygon(xF, xY, 4);
				}
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}