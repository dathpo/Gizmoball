package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

<<<<<<< HEAD
public class CircleBumper implements IBumper, IModel { 
=======
public class CircleBumper implements IBumper { 
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e

	private Circle circle;
	private double x, y;
	private Color colour;
<<<<<<< HEAD
	private static final double SIZE=10, RADIUS=10;

	public CircleBumper(String gizmoName, double x, double y, Color c) {
		this.x = x; // Centre coordinates
		this.y = y;
		this.circle = new Circle(x+SIZE, y+SIZE, RADIUS);
		this.setColour(c);
=======
	private static final double L = 20;
	private static final double RADIUS = 10;

	public CircleBumper(double x, double y, Color c) {
		this.x = (L*x)+RADIUS; // Centre coordinates
		this.y = (L*y)+RADIUS;
		this.colour = c;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}

	public List<LineSegment> getLineSegments() {
		return new ArrayList<LineSegment>();
	}

	@Override
	public List<Circle> getCircles() {
<<<<<<< HEAD
		List<Circle> l = new ArrayList<Circle>();
		l.add(circle);
		return l;
=======
		this.circle = new Circle(x, y, RADIUS);
		List<Circle> circles = new ArrayList<Circle>();
		circles.add(circle);
		return circles;
>>>>>>> 8083294076cfb3979c90e1838caa1bad3956288e
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public Color getColour() {
		return colour;
	}

	public void setColour(Color colour) {
		this.colour = colour;
	}
}
