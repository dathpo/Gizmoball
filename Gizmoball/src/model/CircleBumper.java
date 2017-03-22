package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class CircleBumper implements IBumper { 

	private Circle circle;
	private double x, y;
	private Color colour;
	private String gizmoName;
	private static final double L = 20;
	private static final double RADIUS = 10;
	private boolean deleted = false;
	List<Circle> circles;

	public CircleBumper(String gizmoName, double x, double y, Color c) {
		this.x = (L*x)+RADIUS; // Centre coordinates
		this.y = (L*y)+RADIUS;
		this.colour = c;
		this.gizmoName = gizmoName;
	}

	public List<LineSegment> getLineSegments() {
		return new ArrayList<LineSegment>();
	}

	@Override
	public List<Circle> getCircles() {
		this.circle = new Circle(x, y, RADIUS);
		circles = new ArrayList<Circle>();
		if (!deleted) {
			circles.add(circle);
		}
		return circles;
	}

	public void setX(double xPos) {
		this.x = xPos;
	}

	public void setY(double yPos) {
		this.y = yPos;
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

	@Override
	public String getGizmoName() {
		return gizmoName;
	}

	@Override
	public void rotate() {
	}

	@Override
	public void delete() {
		this.deleted = true;
	}

	@Override
	public void move(double x, double y) {
		setX((x*L)+RADIUS);
		setY((y*L)+RADIUS);
	}

	@Override
	public int getRotations() {
		return 0;
	}
}