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
	private boolean deleted = false;
	List<LineSegment> lineSegments;
	List<Circle> circles;	

	public TriangleBumper(String gizmoName, double x, double y, Color c) {
		this.x = x * L;
		this.y = y * L;
		this.colour = c;
		this.gizmoName = gizmoName;
	}

	@Override
	public List<LineSegment> getLineSegments() {
		lineSegments = new ArrayList<LineSegment>();
		if (!deleted) {
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
			} else if (rotated == 4) {
				this.rotated=0;
			}
		}
		return lineSegments;
	}

	@Override
	public List<Circle> getCircles() {
		circles = new ArrayList<Circle>();
		if (!deleted) {
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
			} else if (rotated == 4) {
				this.rotated=0;
			}
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

	public void setX(double xPos) {
		this.x = xPos;
	}

	public void setY(double yPos) {
		this.y = yPos;
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

	@Override
	public void delete() {
		this.deleted = true;
	}

	@Override
	public void move(double x, double y) {
		setX(x*L);
		setY(y*L);
	}
	
	public int getRotations() {
		return rotated;
	}

}