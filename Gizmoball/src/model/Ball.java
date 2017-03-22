package model;

import java.awt.Color;

import physics.Circle;
import physics.Vect;

public class Ball implements IBall {

	private Vect velocity;
	private double x, y;
	private double ix, iy;
	private Vect iv;
	private Color colour;
	private static final double L = 20;
	private static final double RADIUS = 5;
	private static final double DIAMETER = 10;	
	private boolean stopped;
	private String gizmoName;

	// x, y coordinates and x,y velocity
	public Ball(String gizmoName, double x, double y, double xv, double yv, Color c) {
		this.x = (L*x)+DIAMETER; // Centre coordinates
		this.y = (L*y)+DIAMETER;
		ix = x;
		iy = y;
		iv = new Vect(xv,yv);
		this.colour = c;
		this.velocity = new Vect(xv, yv);
		stopped = false;
		this.gizmoName = gizmoName;
	}

	public void setVelo(Vect v) {
		this.velocity = v;
	}

	public void setVelo(double xv, double yv) {
		this.velocity = new Vect (xv, yv);
	}

	public Vect getVelo() {
		return velocity;
	}

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
	
	public void setInitialX(double xPos) {
		this.ix = xPos;
	}

	public void setInitialY(double yPos) {
		this.iy = yPos;
	}

	public double getInitialX() {
		return ix*L+DIAMETER;
	}

	public double getInitialY() {
		return iy*L+DIAMETER;
	}

	public Vect getInitialVelo() {
		return iv;
	}

	public void start() {
		stopped = false;
	}

	public void stop() {
		stopped = true;
	}

	public boolean isStopped() {
		return stopped; 
	}

	public void stopped() {
		this.setVelo(0, 0);		
		this.stopped = true;
	}

	public void setColor(Color color) {
		this.colour = color;
	}

	public Color getColour() {
		return colour;
	}

	public String getGizmoName() {
		return gizmoName;
	}

	public void move(double x, double y) {
		setInitialX((x));
		setInitialY((y));
	}
}