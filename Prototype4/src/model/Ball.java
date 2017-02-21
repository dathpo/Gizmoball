package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public class Ball implements IBall {

	private Vect velocity;
	private double x, y;
	private Color colour;
	private static final double L = 20;
	private static final double RADIUS = 5;
	private boolean stopped;

	// x, y coordinates and x,y velocity
	public Ball(double x, double y, double xv, double yv, Color c) {
		this.x = (L*x)+RADIUS; // Centre coordinates
		this.y = (L*y)+RADIUS;
		this.colour = c;
		this.velocity = new Vect(xv, yv);
		stopped = false;
	}

	public void setVelo(Vect v) {
		this.velocity = v;
	}
	
	public Vect getVelo() {
		return velocity;
	}
	
//	public void setRadius(double r) {
//		this.radius = r;
//	}

	public double getRadius() {
		return RADIUS;
	}

	public Circle getCircle() {
		return new Circle(x, y, RADIUS);
	}
	
	public void setX(double xPos) {
		this.x = xPos;
	}

	public void setY(double yPos) {
		this.y = yPos;
	}

	// Ball specific methods that deal with double precision.
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void start() {
		stopped = false;
	}
	
	public void stop() {
		stopped = true;
	}

	public boolean stopped() {
		return stopped;
	}

	public void setColor(Color color) {
		this.colour = color;
	}
	
	public Color getColour() {
		return colour;
	}

}