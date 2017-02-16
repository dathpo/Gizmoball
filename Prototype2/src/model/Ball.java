package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public class Ball implements IBall {

	private Vect velocity;
	private double radius, x, y;
	private Color colour;
	private boolean stopped;
	private static final double L = 20;

	// x, y coordinates and x,y velocity
	public Ball(double x, double y, double xv, double yv, double r, Color c) {
		this.x = x; // Centre coordinates
		this.y = y;
		this.radius = r;
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
	
	public void setRadius(double r) {
		this.radius = r;
	}

	public double getRadius() {
		return radius;
	}

	public Circle getCircle() {
		return new Circle(x, y, radius);
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
