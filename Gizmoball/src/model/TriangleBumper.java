package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;

public class TriangleBumper implements IBumper {
	private double x, y;
	private Color colour;
	private String gizmoName;
	private static final double L = 20;
	private int rotated = 0;

	public TriangleBumper(String gizmoName, double x, double y, Color c) {
		this.x = x * L;
		this.y = y * L;
		this.colour = Color.BLUE;
		this.gizmoName = gizmoName;
	}

	@Override
	public List<LineSegment> getLineSegments() {
		List<LineSegment> lineSegments = new ArrayList<LineSegment>();
		if (rotated == 0) {
			lineSegments.add(new LineSegment(x, y, x, y + L));
			lineSegments.add(new LineSegment(x, y, x + L, y));
			lineSegments.add(new LineSegment(x, y + L, x + L, y));
		} else if (rotated == 1) {
			lineSegments.add(new LineSegment(x, y, x + L, y + L));
			lineSegments.add(new LineSegment(x, y, x + L, y));
			lineSegments.add(new LineSegment(x + L, y + L, x + L, y));
		} else if (rotated == 2) {
			lineSegments.add(new LineSegment(x + L, y, x + L, y + L));
			lineSegments.add(new LineSegment(x + L, y, x, y + L));
			lineSegments.add(new LineSegment(x, y + L, x + L, y + L));
		} else if (rotated == 3) {
			lineSegments.add(new LineSegment(x, y, x, y + L));
			lineSegments.add(new LineSegment(x, y, x + L, y + L));
			lineSegments.add(new LineSegment(x, y + L, x + L, y + L));
		}
		return lineSegments;
	}

	@Override
	public List<Circle> getCircles() {
		List<Circle> circles = new ArrayList<Circle>();
		if (rotated == 0) {
			circles.add(new Circle(x, y, 0));
			circles.add(new Circle(x + L, y, 0));
			circles.add(new Circle(x, y + L, 0));
		} else if (rotated == 1) {
			circles.add(new Circle(x, y, 0));
			circles.add(new Circle(x + L, y, 0));
			circles.add(new Circle(x + L, y + L, 0));
		} else if (rotated == 2) {
			circles.add(new Circle(x, y + L, 0));
			circles.add(new Circle(x + L, y, 0));
			circles.add(new Circle(x + L, y + L, 0));
		} else if (rotated == 3) {
			circles.add(new Circle(x, y, 0));
			circles.add(new Circle(x, y + L, 0));
			circles.add(new Circle(x + L, y + L, 0));
		}
		return circles;
	}

	public void rotate() {
		rotated++;
	}

	@Override
	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public Color getColour() {
		return colour;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public String getGizmoName() {
		return gizmoName;
	}

}