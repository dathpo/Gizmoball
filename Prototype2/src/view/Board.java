package view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.sound.sampled.Line;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import model.IBall;
import model.IBumper;
import model.IModel;
import model.Model;
import physics.Circle;
import physics.LineSegment;

public class Board extends JPanel implements Observer {

	protected static int width;
	protected static int height;
	private IModel model;
	private static final int L = 20;

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
		//Graphics2D g2 = (Graphics2D) g;
		paintGrid(g);
		paintBall(g);
		paintBumpers(g);
	}

	public void paintGrid(Graphics g) {
		for (int k=0; k<=L; k++) {
			g.drawLine(0, k*height/L, width, k*height/L);
		}
		for (int k=0; k<=L; k++) {
			g.drawLine(k*width/L, 0, k*width/L, height);
		}
	}

	public void paintBall(Graphics g) {
		if (model.getBall()!= null) {
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
		if (model.getBumpers()!= null) {
			for(IBumper bumper : model.getBumpers()){
				List<Circle> circles = bumper.getCircles();
				List<LineSegment> lineSegments = bumper.getLineSegments();
				g.setColor(bumper.getColour());
				if(circles.size()==1){
					int radius = (int) (circles.get(0).getRadius());
					int diameter = (int) (2 * radius);
					int xC = (int) (circles.get(0).getCenter().x() - radius);
					int yC = (int) (circles.get(0).getCenter().y() - radius);
					g.fillOval(xC, yC, diameter, diameter);
				} else if (lineSegments.size()==3) {
					//					int[] xT = new int[3];
					//					int[] yT = new int[3];
					//					xT[0] = (int) x;
					//				    xT[1] = (int) x + L;
					//				    xT[2] = (int) cx;
					//					(int[]) (circles.get(0).getCenter().x());
					//					int[] yT = (int[]) (circles.get(0).getCenter().y());
					//					g.fillPolygon(xT, yT, L);

				} else if (lineSegments.size()==4) {
					int xS = 0;
					int yS = 0;
					for (int i=0; i<4; i++) {
						xS = (int) (circles.get(i).getCenter().x() - (int) lineSegments.get(0).length());
						yS = (int) (circles.get(i).getCenter().y() - (int) lineSegments.get(0).length());
					}
					g.fillRect(xS, yS, (int) lineSegments.get(0).length(), (int) lineSegments.get(0).length());
					//				} else if (circles.size()==4) {
					//					int xS = (int) (circles.get(0).getCenter().x());
					//					int yS = (int) (circles.get(0).getCenter().y());
					//					g.fillRect(xS, yS, L, L);
				}
			}
		}
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
