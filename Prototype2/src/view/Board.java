package view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

<<<<<<< HEAD
import model.Ball;
import model.IBumper;
import model.Model;
import physics.Circle;
=======
import model.IAbsorber;
import model.IBall;
import model.IBumper;
import model.IModel;
import model.Model;
import physics.Circle;
import physics.LineSegment;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e

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
<<<<<<< HEAD
//		Graphics2D g2 = (Graphics2D) g;
		paintBall(g);
		paintSquareBumpers(g);
		paintBumpers(g);
	}

	public void paintBall(Graphics g) {
		Ball b = model.getBall();
		if (b != null) {
			g.setColor(b.getColour());
			int x = (int) (b.getExactX() - b.getRadius());
			int y = (int) (b.getExactY() - b.getRadius());
			int width = (int) (2 * b.getRadius());
			g.fillOval(x, y, width, width);
		}
	}
	
	public void paintSquareBumpers(Graphics g) {
		List<IBumper> b = model.getBumpers();	
	}
	
	public void paintBumpers(Graphics g) {
		if(model.getBumpers()!=null){
			for(IBumper bumper : model.getBumpers()){
				List<Circle> circles = bumper.getCircles();
				g.setColor(bumper.getColour());
				if(circles.size()==1){
					int radius = (int)circles.get(0).getRadius();
					g.fillOval((int)circles.get(0).getCenter().x()-radius, (int)circles.get(0).getCenter().y()-radius, radius*2, radius*2);
				
=======
		//Graphics2D g2 = (Graphics2D) g;
		paintGrid(g);
		paintBall(g);
		paintBumpers(g);
		paintAbsorbers(g);
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
				if(circles.size()>lineSegments.size()){
					int radius = (int) (circles.get(0).getRadius());
					int diameter = (int) (2 * radius);
					int xC = (int) (circles.get(0).getCenter().x() - radius);
					int yC = (int) (circles.get(0).getCenter().y() - radius);
					g.fillOval(xC, yC, diameter, diameter);
				} else if ((lineSegments.size() & circles.size()) == 3) {
					int[] xT = new int[3];
					int[] yT = new int[3];
					int lengthS = circles.size();
					for(int i=0; i<3; i++){
						xT[i] = (int) circles.get(i).getCenter().x();
						yT[i] = (int) circles.get(i).getCenter().y();
					}
					g.fillPolygon(xT, yT, lengthS);
				} else if ((lineSegments.size() & circles.size()) == 4) {
					int xS = 0;
					int yS = 0;
					int lengthS = (int) lineSegments.get(0).length();
					for (int i=0; i<4; i++) {
						xS = (int) (circles.get(i).getCenter().x() - (int) lineSegments.get(i).length());
						yS = (int) (circles.get(i).getCenter().y() - (int) lineSegments.get(i).length());
					}
					g.fillRect(xS, yS, lengthS, lengthS);
				}
			}
		}
	}

	public void paintAbsorbers(Graphics g) {
		if (model.getAbsorbers()!= null) {
			for(IAbsorber absorber : model.getAbsorbers()){
				List<Circle> circles = absorber.getCircles();
				List<LineSegment> lineSegments = absorber.getLineSegments();
				g.setColor(absorber.getColour());
				if ((lineSegments.size() == circles.size())) {
					int xS = 0;
					int yS = 0;
					int lengthS = (int) lineSegments.get(0).length();
					for (int i=0; i<4; i++) {
						xS = (int) (circles.get(i).getCenter().x() - (int) lineSegments.get(i).length());
						yS = (int) (circles.get(i).getCenter().y() - (int) lineSegments.get(i).length());
					}
					g.fillRect(xS, yS, lengthS, lengthS);
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
				}
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
