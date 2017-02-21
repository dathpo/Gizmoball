package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class CircleBumper implements IBumper, IModel { 

	private Circle circle;
	private double x, y;
	private Color colour;
	private static final double SIZE=10, RADIUS=10;

	public CircleBumper(String gizmoName, double x, double y, Color c) {
		this.x = x; // Centre coordinates
		this.y = y;
		this.circle = new Circle(x+SIZE, y+SIZE, RADIUS);
		this.setColour(c);
	}

	public List<LineSegment> getLineSegments() {
		return new ArrayList<LineSegment>();
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> l = new ArrayList<Circle>();
		l.add(circle);
		return l;
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
